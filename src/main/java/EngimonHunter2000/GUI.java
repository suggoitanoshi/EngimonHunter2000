package EngimonHunter2000;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {
    public GUI() {
        GameState gs=new GameState();

        setTitle("EngimonHunter2000");
        setSize(1156, 640);
        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(new Dimension(640, 480));
        MapGrid m = new MapGrid(gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());

        JPanel container_control = new JPanel(new BorderLayout());
        JPanel container_menu = new JPanel(new GridLayout(5, 2, 5, 5));
        JPanel container_hasil = new JPanel();
        JPanel control = new JPanel();
        JPanel panel = new JPanel(new GridLayout(3, 8, 0, 0));

        // movement
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && j == 4) {
                    JButton w = new JButton("w");
                    w.setPreferredSize(new Dimension(60, 30));
                    w.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gs.getPlayer().setPositionY(gs.getPlayer().getPositionY() - 1);
							gs.getPlayer().setDir('w');
							gs.updateGameState();
                            pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                        }
                    });
                    panel.add(w);
                } else if (i == 1 && j == 3) {
                    JButton a = new JButton("a");
                    a.setPreferredSize(new Dimension(60, 30));
                    a.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gs.getPlayer().setPositionX(gs.getPlayer().getPositionX() - 1);
							gs.getPlayer().setDir('a');
							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                        }
                    });
                    panel.add(a);
                } else if (i == 2 && j == 4) {
                    JButton s = new JButton("s");
                    s.setPreferredSize(new Dimension(60, 30));
                    s.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gs.getPlayer().setPositionY(gs.getPlayer().getPositionY() + 1);
							gs.getPlayer().setDir('s');
							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                        }
                    });
                    panel.add(s);
                } else if (i == 1 && j == 5) {
                    JButton d = new JButton("d");
                    d.setPreferredSize(new Dimension(60, 30));
                    d.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gs.getPlayer().setPositionX(gs.getPlayer().getPositionX() + 1);
							gs.getPlayer().setDir('d');
							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                        }
                    });
                    panel.add(d);
                } else {
                    JLabel l = new JLabel();
                    panel.add(l);
                }
            }
        }
        // movement done

        control.add(panel);

        // menu start
        JButton lihat1 = new JButton("Lihat list engimon pemain");
        JButton lihat2 = new JButton("Lihat seluruh list engimon");
        JButton ganti = new JButton("Ganti active engimon");
        JButton skill = new JButton("Lihat skill item pemain");
        JButton item = new JButton("Gunakan skill item");
        JButton breed = new JButton("Lakukan breeding");
        JButton battle = new JButton("Lakukan battle");
        JButton engi = new JButton("Lihat data lengkap engimon pemain");
        JButton interact = new JButton("Interaksi dengan engimon");
        JButton save = new JButton("Save game");

        container_menu.setSize(new Dimension(576, 200));
        container_menu.add(lihat1);
        container_menu.add(lihat2);
        container_menu.add(ganti);
        container_menu.add(skill);
        container_menu.add(item);
        container_menu.add(breed);
        container_menu.add(battle);
        container_menu.add(engi);
        container_menu.add(interact);
        container_menu.add(save);
        container_control.add(container_menu, BorderLayout.NORTH);
        // menu end
        control.add(panel);

        // hasil start
        container_hasil.setSize(new Dimension(576, 190));
        container_control.add(container_hasil, BorderLayout.CENTER);
        // hasil end

        pane.add(m, 1);
        add(pane, BorderLayout.WEST);
        container_control.setSize(new Dimension(512, 480));
        container_control.add(control, BorderLayout.SOUTH);
        add(container_control, BorderLayout.EAST);
        control.setSize(new Dimension(512, 90));
        container_control.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MapGrid extends JPanel {
    public MapGrid(int x_player, int y_player, Tile[][] map) {
        JPanel panel = new JPanel(new GridLayout(20, 20, 0, 0));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
				JLabel l = new JLabel(new ImageIcon(map[i][j].getPath()));
				panel.add(l);
            }
        }
        add(panel);
        setSize(new Dimension(640, 640));
    }
}
