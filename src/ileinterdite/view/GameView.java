package ileinterdite.view;

import ileinterdite.model.adventurers.Adventurer;
import ileinterdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameView {
    private JFrame window;

    private JPanel bottomPanel;
    private JPanel advViewPanel;

    private JPanel gridPanel;
    private JPanel handsPanel;
    private JPanel waterScalePanel;

    public GameView(int width, int height) {
        SwingUtilities.invokeLater(() -> initView(width, height));
    }

    private void initView(int width, int height) {
        this.window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        window.add(bottomPanel, BorderLayout.SOUTH);

        advViewPanel = new JPanel();
        bottomPanel.add(advViewPanel);

        /* We put the gridPanel into a panel with a GridBagLayout to take the "GetPreferredSize" in account
           and have a squared grid */
        JPanel gridContenant = new JPanel(new GridBagLayout());
        gridPanel = new JPanel(new GridLayout(1,1)) {
            // Code inspired from https://coderanch.com/t/629253/java/create-JPanel-maintains-aspect-ratio#2880512
            // By Louis Lewis

            /**
             * Override the preferred size to return the largest it can, in a square shape.
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize;
                Component c = getParent();
                if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();

                int s = Math.min(w, h);
                return new Dimension(s,s);
            }
        };

        gridPanel.setLayout(new GridLayout(1, 1));
        gridContenant.add(gridPanel);
        window.add(gridContenant, BorderLayout.CENTER);

        handsPanel = new JPanel();
        window.add(handsPanel, BorderLayout.EAST);

        waterScalePanel = new JPanel(new GridLayout(1, 1));
        window.add(waterScalePanel, BorderLayout.WEST);
    }

    public void setVisible() {
        SwingUtilities.invokeLater(() -> window.setVisible(true));
    }

    public void setAdventurerView(AdventurerView view) {
        SwingUtilities.invokeLater(() -> {
            advViewPanel.removeAll();
            advViewPanel.add(view.getMainPanel());
            advViewPanel.revalidate();
        });
    }

    public void setGridView(GridView view) {
        SwingUtilities.invokeLater(() -> {
            gridPanel.add(view.getMainPanel());
            gridPanel.revalidate();
        });
    }

    public void setTreasureView(TreasureView treasureView) {
        SwingUtilities.invokeLater(() -> {
            bottomPanel.add(treasureView.getMainPanel());
            bottomPanel.revalidate();
        });
    }
    
    public void setHandViews(HashMap<Adventurer, HandView> handViews) {
        SwingUtilities.invokeLater(() -> {
            handsPanel.setLayout(new GridLayout(handViews.size(), 1));
            for (Adventurer adv : handViews.keySet()) {
                JPanel tempPanel = new JPanel();
                tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
                tempPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

                tempPanel.add(new JLabel(adv.getName() + " (" + adv.getClassName() + ")"));
                tempPanel.add(handViews.get(adv).getMinimizedPanel());
                handsPanel.add(tempPanel);
            }
        });
    }
    
    public void setWaterScaleView(WaterScaleView view) {
        SwingUtilities.invokeLater(() -> {
            waterScalePanel.add(view.getMainPanel(), BorderLayout.WEST);
            waterScalePanel.revalidate();
        });
    }

    public void showVictory(VictoryView view) {
        SwingUtilities.invokeLater(() -> showEndGame(view.getMainPanel()));
    }

    public void showDefeat(DefeatView view) {
        SwingUtilities.invokeLater(() -> showEndGame(view.getMainPanel()));
    }
    
    private void showEndGame(JPanel panel) {
        window.getContentPane().removeAll();
        window.getContentPane().add(panel);
        window.revalidate();
        panel.repaint();
    }
}
