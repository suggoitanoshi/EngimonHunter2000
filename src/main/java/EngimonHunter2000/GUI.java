package EngimonHunter2000;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.SkeletonMismatchException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.*;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private GameState gs;

    public GUI() {
        gs=new GameState();

        setTitle("EngimonHunter2000");
        setSize(1156, 704);
        JLayeredPane pane = new JLayeredPane();
        JPanel innerBox = new JPanel(new BorderLayout());
        JLayeredPane outerpane = new JLayeredPane();
        pane.setSize(new Dimension(1156, 704));
        pane.setPreferredSize(new Dimension(640, 480));
        MapGrid m = new MapGrid(gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());

        JPanel container_control = new JPanel(new BorderLayout());
        JPanel container_menu = new JPanel(new GridLayout(5, 2, 5, 5));
        JPanel container_hasil = new JPanel(new GridBagLayout());
        JScrollPane hasil_outer = new JScrollPane(container_hasil);
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
                            if (gs.canMoveEntity(0, -1,
                                                 gs.getPlayer().getPositionX(),
                                                 gs.getPlayer().getPositionY())) {
                                gs.getPlayer().setPositionY(gs.getPlayer().getPositionY() - 1);
                                gs.applyPlayerPosition();
                                gs.applyActiveEngimonPosition();
                            }
							gs.getPlayer().setDir('w');
                            gs.updateGameState();
                            pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                            container_hasil.removeAll();
                        }
                    });
                    panel.add(w);
                } else if (i == 1 && j == 3) {
                    JButton a = new JButton("a");
                    a.setPreferredSize(new Dimension(60, 30));
                    a.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gs.canMoveEntity(-1, 0,
                                                 gs.getPlayer().getPositionX(),
                                                 gs.getPlayer().getPositionY())) {
                                gs.getPlayer().setPositionX(gs.getPlayer().getPositionX() - 1);
                                gs.applyPlayerPosition();
                                gs.applyActiveEngimonPosition();
                            }
							gs.getPlayer().setDir('a');
							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                            container_hasil.removeAll();
                        }
                    });
                    panel.add(a);
                } else if (i == 1 && j == 4) {
                    JButton s = new JButton("s");
                    s.setPreferredSize(new Dimension(60, 30));
                    s.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gs.canMoveEntity(0, 1,
                                                 gs.getPlayer().getPositionX(),
                                                 gs.getPlayer().getPositionY())) {
                                gs.getPlayer().setPositionY(gs.getPlayer().getPositionY() + 1);
                                gs.applyPlayerPosition();
                                gs.applyActiveEngimonPosition();
                            }
							gs.getPlayer().setDir('s');
                            gs.updateGameState();
                            pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                            container_hasil.removeAll();
                        }
                    });
                    panel.add(s);
                } else if (i == 1 && j == 5) {
                    JButton d = new JButton("d");
                    d.setPreferredSize(new Dimension(60, 30));
                    d.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gs.canMoveEntity(1, 0,
                                                 gs.getPlayer().getPositionX(),
                                                 gs.getPlayer().getPositionY())) {
                                gs.getPlayer().setPositionX(gs.getPlayer().getPositionX() + 1);
                                gs.applyPlayerPosition();
                                gs.applyActiveEngimonPosition();
                            }
							gs.getPlayer().setDir('d');
							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                            container_hasil.removeAll();
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

        GridBagConstraints c = new GridBagConstraints();

        // HANDLER EVENT
        lihat1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                container_hasil.removeAll();
                c.gridx = 0;
                Inventory<Engimon> engies = gs.getPlayer().getInventoryEngimon();
                HashMap<String, ArrayList<Engimon>> groupByElement = new HashMap<String, ArrayList<Engimon>>();
                for(int i = 0; i < engies.getAllInvenTotalItemCount(); i++){
                    try{
                        Engimon en = engies.at(i);
                        String el = ((Element)en.getListElement().getElementsList().toArray()[0]).name();
                        if(groupByElement.get(el) == null){
                            groupByElement.put(el, new ArrayList<Engimon>());
                        }
                        groupByElement.get(el).add(en);
                    } catch(InventoryException ex){}
                }
                c.gridy = 0;
                for(ArrayList<Engimon> anjay: groupByElement.values()){
                    java.util.List<Engimon> sorted = anjay.stream().sorted((e1, e2)->{ return Integer.compare(e1.getLvl(), e2.getLvl()); }).collect(Collectors.toList());
                    for(Engimon curr: sorted){
                        try{
                            c.gridx = 0;
                            c.gridy++;
                            int j = engies.getItemFromIdx(curr);
                            String species = curr.getSpecies().toLowerCase().replaceAll("\\s", "");
                            ImageIcon icon = new ImageIcon("data/resource/"+species+"/"+species+".png");
                            JButton gantinama = new JButton("Ganti namanya");
                            JButton hapus = new JButton("Hapus yang ini");
                            gantinama.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    container_hasil.removeAll();
                                    JTextField namanya = new JTextField();
                                    JButton ok = new JButton("Ok");

                                    namanya.setColumns(20);

                                    ok.addActionListener(new ActionListener(){
                                        @Override
                                        public void actionPerformed(ActionEvent e){
                                            try{
                                                gs.getPlayer().changeEngimonName(j, namanya.getText());
                                                container_hasil.removeAll();
                                                container_hasil.revalidate();
                                                container_hasil.repaint();
                                            } catch(EngimonHunter2000Exception ex){}
                                        }
                                    });

                                    container_hasil.add(new JLabel("Nama baru: "));
                                    container_hasil.add(namanya);
                                    container_hasil.add(ok);
                                    container_hasil.revalidate();
                                    container_hasil.repaint();
                                }
                            });
                            hapus.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    try {
                                        if (gs.getPlayer().getInventoryEngimon().getItemCount() > 1) {
                                            Engimon ea = gs.getPlayer().getActiveEngimon();
                                            Engimon yangMauDiapus = gs.getPlayer().getInventoryEngimon().at(j);
                                            gs.getPlayer().removeEngimon(j);
                                            if (ea.equals(yangMauDiapus)) {
                                                // ganti engimonnya
                                                gs.getPlayer().switchEngimon(0);
                                            }
                                            container_hasil.removeAll();
                                            container_hasil.revalidate();
                                            container_hasil.repaint();
                                        } else {
                                            container_hasil.removeAll();
                                            c.gridx = 0;
                                            c.gridy = 0;
                                            container_hasil.add(new JLabel("GAME OVER GAN"), c);
                                            c.gridy = 1;
                                            container_hasil.add(new JLabel("SIAPA SURUH NGAPUS AKTIF ENGIMON"), c);
                                            container_hasil.revalidate();
                                            container_hasil.repaint();
                                            gs.setGameOver();
                                        }
                                    } catch(InventoryException ex){
                                        ex.printStackTrace();
                                        container_hasil.removeAll();
                                        container_hasil.revalidate();
                                        container_hasil.repaint();
                                    } finally {
                                        gs.updateGameState();
                                        pane.removeAll();
                                        MapGrid m = new MapGrid(
                                            gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                                        pane.add(m, BorderLayout.WEST);
                                        pane.revalidate();
                                        pane.repaint();
                                    }
                                }
                            });
                            container_hasil.add(new JLabel(curr.getName(), icon, SwingConstants.LEFT), c);
                            c.gridx = 1;
                            container_hasil.add(gantinama, c);
                            c.gridx = 2;
                            container_hasil.add(hapus, c);
                        } catch(InventoryException ex){}
                    }
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        lihat2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                EngiDex dex = gs.getEDex();
                Object[] lengkap = dex.getDex().values().toArray();
                c.gridx = 0;
                for(int i = 0; i < dex.getDex().size(); i++){
                    c.gridy = i;
                    String species = ((EngimonSpecies)lengkap[i]).getSpecies();
                    String speciesanjay = species.replaceAll("\\s", "");
                    ImageIcon icon = new ImageIcon("data/resource/"+speciesanjay+"/"+speciesanjay+".png");
                    container_hasil.add(new JLabel(species, icon, SwingConstants.LEFT), c);
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        ganti.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                Inventory<Engimon> engies = gs.getPlayer().getInventoryEngimon();
                HashMap<String, ArrayList<Engimon>> groupByElement = new HashMap<String, ArrayList<Engimon>>();
                for(int i = 0; i < engies.getAllInvenTotalItemCount(); i++){
                    try{
                        Engimon en = engies.at(i);
                        String el = ((Element)en.getListElement().getElementsList().toArray()[0]).name();
                        if(groupByElement.get(el) == null){
                            groupByElement.put(el, new ArrayList<Engimon>());
                        }
                        groupByElement.get(el).add(en);
                    } catch(InventoryException ex){}
                }
                c.gridy = 0;
                for(ArrayList<Engimon> anjay: groupByElement.values()){
                    java.util.List<Engimon> sorted = anjay.stream().sorted((e1, e2)->{ return Integer.compare(e1.getLvl(), e2.getLvl()); }).collect(Collectors.toList());
                    for(Engimon curr: sorted){
                        try{
                            c.gridx = 0;
                            c.gridy++;
                            int j = engies.getItemFromIdx(curr);
                            String species = curr.getSpecies().toLowerCase().replaceAll("\\s", "");
                            ImageIcon icon = new ImageIcon("data/resource/"+species+"/"+species+".png");
                            JButton button = new JButton(curr.getName(), icon);
                            button.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    try{
                                        gs.getPlayer().switchEngimon(j);

                                        gs.updateGameState();
                                        pane.removeAll();
                                        MapGrid m = new MapGrid(
                                            gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                                        pane.add(m, BorderLayout.WEST);
                                        pane.revalidate();
                                        pane.repaint();
                                    } catch(InventoryException ex){}
                                    container_hasil.removeAll();
                                    container_hasil.revalidate();
                                    container_hasil.repaint();
                                }
                            });
                            container_hasil.add(button, c);
                        } catch(InventoryException ex){}
                    }
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        skill.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                c.gridx = 0;
                Inventory<Item> items = gs.getPlayer().getInventoryItem();
                java.util.List<Item> itemsorted = new java.util.ArrayList<Item>();
                for(int i = 0; i < items.getAllInvenTotalItemCount(); i++){
                    try{
                        itemsorted.add(items.at(i));
                    }
                    catch(InventoryException ex){}
                }
                itemsorted = itemsorted.stream().sorted((i1, i2)->{ return Integer.compare(i1.getBasePower(), i2.getBasePower()); }).collect(Collectors.toList());
                int gridy = 0;
                for(Item i: itemsorted){
                    try{
                        int j = items.getItemFromIdx(i);
                        String realname = i.getName();
                        String filename = realname.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                        c.gridx = 0;
                        c.gridy = gridy++;
                        ImageIcon icon = new ImageIcon("data/resource/skills/"+filename+".png");
                        JButton liatdetail = new JButton("Lihat detail");
                        JButton hapusgan = new JButton("Hapus Item");
                        liatdetail.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                container_hasil.removeAll();
                                c.gridx = 0;
                                c.gridy = 0;
                                container_hasil.add(new JLabel("Detail Item:", icon, SwingConstants.LEFT), c);
                                c.gridy = 1;
                                container_hasil.add(new JLabel("Base power: "+i.getBasePower()), c);
                                StringBuilder els = new StringBuilder();
                                els.append("Elements: ");
                                for(Element elem: i.getElements()){
                                    els.append(elem.name()+", ");
                                }
                                c.gridy = 2;
                                container_hasil.add(new JLabel(els.toString()), c);
                                container_hasil.revalidate();
                                container_hasil.repaint();
                            }
                        });
                        hapusgan.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                container_hasil.removeAll();
                                JSpinner num = new JSpinner();
                                JButton anjay = new JButton("OK");

                                anjay.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        try{
                                            num.commitEdit();
                                            gs.getPlayer().removeItem(j, (Integer)num.getValue());
                                            container_hasil.removeAll();
                                        } catch(Exception ex){}
                                    }
                                });

                                container_hasil.add(new JLabel("Masukkan jumlah: "));
                                container_hasil.add(num);
                                container_hasil.add(anjay);
                            }
                        });
                        container_hasil.add(new JLabel(realname, icon, SwingConstants.LEFT), c);
                        c.gridx = 1;
                        container_hasil.add(liatdetail, c);
                        c.gridx = 2;
                        container_hasil.add(hapusgan,c);
                    } catch(InventoryException ex){}
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                Inventory<Item> items = gs.getPlayer().getInventoryItem();
                java.util.List<Item> itemsorted = new java.util.ArrayList<Item>();
                for(int i = 0; i < items.getAllInvenTotalItemCount(); i++){
                    try{
                        itemsorted.add(items.at(i));
                    }
                    catch(InventoryException ex){}
                }
                itemsorted = itemsorted.stream().sorted((i1, i2)->{ return Integer.compare(i1.getBasePower(), i2.getBasePower()); }).collect(Collectors.toList());
                for(Item i: itemsorted){
                    try{
                        int j = items.getItemFromIdx(i);
                        String namaasli = i.getName();
                        String namapalsu = namaasli.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                        ImageIcon icon = new ImageIcon("data/resource/skills/"+namapalsu+".png");
                        JButton button = new JButton(namaasli, icon);
                        button.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if(gs.getPlayer().getActiveEngimon().getSkillCount() == 4){
                                    container_hasil.removeAll();
                                    JComboBox combo = new JComboBox(i.getElements().stream().map((item)->{return item.name();}).collect(Collectors.toList()).toArray());
                                    JButton ok = new JButton("OK");
                                    ok.addActionListener(new ActionListener(){
                                        @Override
                                        public void actionPerformed(ActionEvent e){
                                            try{
                                                gs.getPlayer().getActiveEngimon().delSkill(gs.getPlayer().getActiveEngimon().getSkillByString((String)combo.getSelectedItem()));
                                                gs.getPlayer().useItem(j);
                                            }catch(EngimonHunter2000Exception ex){}
                                            container_hasil.removeAll();
                                            container_hasil.revalidate();
                                            container_hasil.repaint();
                                        }
                                    });
                                    container_hasil.add(new JLabel("Pilih skill yang akan ditimpa: "));
                                    container_hasil.add(combo);
                                    container_hasil.add(ok);
                                    container_hasil.revalidate();
                                    container_hasil.repaint();
                                }
                                try{
                                    gs.getPlayer().useItem(j);
                                } catch(ItemException | SkillEngimonException exp) {
                                    JOptionPane
                                        .showMessageDialog(container_hasil, exp.what());
                                } catch (InventoryException exp) {}
                                container_hasil.removeAll();
                                container_hasil.revalidate();
                                container_hasil.repaint();
                            }
                        });
                        container_hasil.add(button, c);
                    } catch(InventoryException ex){}
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        Engimon[] parents = new Engimon[2];
        breed.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                parents[0] = null;
                parents[1] = null;
                container_hasil.removeAll();
                Inventory<Engimon> engies = gs.getPlayer().getInventoryEngimon();
                c.gridx = 0;
                c.gridy = 0;
                JLabel kawinlabel = new JLabel("Pilih orangtua 1: ");
                container_hasil.add(new JLabel("Kawin~"));
                container_hasil.add(kawinlabel);
                for(int i = 0; i < engies.getAllInvenTotalItemCount(); i++){
                    try{
                        c.gridy = i+2;
                        int j = i;
                        Engimon curr = engies.at(i);
                        String species = curr.getSpecies().toLowerCase().replaceAll("\\s", "");
                        ImageIcon icon = new ImageIcon("data/resource/"+species+"/"+species+".png");
                        JButton button = new JButton(curr.getName(), icon);
                        button.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                try{
                                    if(parents[0] == null){
                                        kawinlabel.setText("Pilih orangtua 2: ");
                                        parents[0] = gs.getPlayer().getInventoryEngimon().at(j);
                                    }
                                    else{
                                        parents[1] = gs.getPlayer().getInventoryEngimon().at(j);
                                        container_hasil.removeAll();
                                        container_hasil.add(new JLabel("Nama: "));
                                        JTextField namaAnak = new JTextField();
                                        namaAnak.setColumns(20);
                                        JButton ok = new JButton("OK!");
                                        JButton cancel = new JButton("Cancel");
                                        ok.addActionListener(new ActionListener(){
                                            @Override
                                            public void actionPerformed(ActionEvent e){
                                                Breeding b = new Breeding();
                                                try{
                                                    Engimon anak = b.breeding(gs.getEDex(), parents[0], parents[1], gs.getSkillDex(), namaAnak.getText());
                                                    gs.getPlayer().addEngimon(anak);
                                                } catch(EngimonHunter2000Exception ex){}
                                                container_hasil.removeAll();
                                            }
                                        });
                                        container_hasil.add(namaAnak);
                                        container_hasil.add(ok);
                                        container_hasil.add(cancel);
                                        container_hasil.revalidate();
                                        container_hasil.repaint();
                                    }
                                } catch(InventoryException ex){}
                            }
                        });
                        container_hasil.add(button, c);
                    } catch(InventoryException ex){}
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        battle.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                java.util.List<Engimon> engs = gs
                    .getWildEngimons().stream().filter((engi) -> {
                        return engi
                            .getPosition()
                            .distanceTo(gs.getPlayer().getPosition()) < 2;
                    }).collect(Collectors.toList());
                c.gridx = 0;
                for(int i = 0; i < engs.size(); i++){
                    c.gridy = i;
                    Engimon en = engs.get(i);
                    String species = en.getSpecies();
                    String iconpath = species.toLowerCase().replaceAll("\\s", "");
                    ImageIcon icon = new ImageIcon("data/resource/"+iconpath+"/"+iconpath+".png");
                    JButton button = new JButton(species, icon);
                    button.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            Battle gelud = new Battle();
                            gelud.checkAdvantage(gs.getPlayer().getActiveEngimon(), en);
                            StringBuilder detailEngiMusuh = new StringBuilder();
                            // spesies, elemen, level,
                            detailEngiMusuh.append("Spesies: "+en.getSpecies()+"\n");
                            ElementsList els = en.getListElement();
                            detailEngiMusuh.append("Elemen: ");
                            for(Element el: els.getElementsList()){
                                detailEngiMusuh.append(el+", ");
                            }
                            detailEngiMusuh.append("\n");

                            detailEngiMusuh.append("Level: "+en.getLvl()+"\n");
                            detailEngiMusuh.append("\n\n");
                            detailEngiMusuh.append("Power: \n");
                            detailEngiMusuh.append("Punyamu>");
                            detailEngiMusuh.append(gs.getPlayer().getActiveEngimon().getName()+": "+gs.getPlayer().getActiveEngimon().getBattlePower(gelud.getAdvA()));
                            detailEngiMusuh.append("\nWild Engimon>");
                            detailEngiMusuh.append(en.getSpecies()+": "+en.getBattlePower(gelud.getAdvB()));

                            int jadigak = JOptionPane.showConfirmDialog(container_hasil, detailEngiMusuh.toString(), "Konfirmasi gelud", JOptionPane.YES_NO_OPTION);
                            if(jadigak == JOptionPane.NO_OPTION) return;

                            boolean menangkh = gelud.runBattle(gs.getPlayer().getActiveEngimon(), en);

                            if(!menangkh) { // kalah
								JOptionPane.showMessageDialog(container_hasil, "kalah");
								try{
                                    try {
                                        gs.getPlayer().getActiveEngimon().setLives(-1);
                                        if (gs.getPlayer().getActiveEngimon().getLives() <= 0) {
                                            throw new EngimonException(2);
                                        }
                                    } catch (EngimonException exp) {
                                        if (gs.getPlayer().getInventoryEngimon().getItemCount() == 1){
                                            gs.setGameOver();
                                            container_hasil.removeAll();
                                            c.gridx = 0;
                                            c.gridy = 0;
                                            container_hasil.add(new JLabel("GAME OVER GAN"), c);
                                            container_hasil.revalidate();
                                            container_hasil.repaint();
                                        } else {
                                            gs.getPlayer().removeActiveEngimon();
                                        }
                                    }
								} catch (InventoryException e1){
                                    e1.printStackTrace();
                                    gs.setGameOver(); // dibikin game over
								}
							} else { // menang
								JOptionPane.showMessageDialog(container_hasil, "menang");
								try{
                                    en.setLives(3);
                                    gs.getWildEngimons().remove(en);
                                    gs.getPlayer().addEngimon(en);
                                    Skill itSkill = gs
                                                    .getSkillDex()
                                                    .getEntity(en
                                                        .getStarterSkill()
                                                        .getName());
                                    gs.getPlayer().addItem(new Item(itSkill));
								}
								catch (InventoryException | EngimonException e1){
                                    e1.printStackTrace();
								}
							}

							gs.updateGameState();
							pane.removeAll();
                            MapGrid m = new MapGrid(
                                gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                            pane.add(m, BorderLayout.WEST);
                            pane.revalidate();
                            pane.repaint();
                            container_hasil.removeAll();
                            container_hasil.repaint();
                        }
                    });
                    container_hasil.add(button, c);
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        engi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                c.gridx = 0;
                c.gridy = 0;
                Engimon engi = gs.getPlayer().getActiveEngimon();
                container_hasil.add(new JLabel("Engimon "+engi.getName()+":"), c);
                String[] parents = engi.getParents();
                c.gridy++;
                StringBuilder elems = new StringBuilder();
                elems.append("Elements: ");
                for(Element el: engi.getListElement().getElementsList()){
                    elems.append(el.name()+", ");
                }
                container_hasil.add(new JLabel(elems.toString()), c);
                c.gridy++;
                container_hasil.add(new JLabel("Orangtua 1: "+parents[0]), c);
                c.gridy++;
                container_hasil.add(new JLabel("Orangtua 2: "+parents[1]), c);
                c.gridy++;
                container_hasil.add(new JLabel("Lives: "+engi.getLives()), c);
                c.gridy++;
                container_hasil.add(new JLabel("Level: "+engi.getLvl()), c);
                c.gridy++;
                container_hasil.add(new JLabel("Exp: "+engi.getExp()), c);
                c.gridy++;
                container_hasil.add(new JLabel("Cum. Exp: "+engi.getCExp()), c);
                c.gridy++;
                container_hasil.add(new JLabel("Skills: "), c);
                for(SkillEngimon s: engi.getSkills()){
                    c.gridy++;
                    String skillname = s.getName() + "(" + s.getMasteryLevel() + ")";
                    String stripped = skillname.toLowerCase().replace("\\s", "");
                    ImageIcon icon = new ImageIcon("data/resource/skills/"+stripped+".png");
                    container_hasil.add(new JLabel(skillname, icon, SwingConstants.LEFT), c);
                }
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        interact.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                Engimon engi = gs.getPlayer().getActiveEngimon();
                String species = engi.getSpecies().toLowerCase().replaceAll("\\s", "");
                ImageIcon icon = new ImageIcon("data/resource/"+species+"/"+species+".png");
                container_hasil.add(new JLabel(engi.getName() + ": "+ engi.getSlogan(), icon, SwingConstants.LEFT));
                container_hasil.revalidate();
                container_hasil.repaint();
            }
        });

        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                container_hasil.removeAll();
                try{
                    gs.save("data/save.dat");
                } catch(GameStateException ex){
                    container_hasil.add(new JLabel("Terjadi kesalahan ketika mencoba save, yahaha gabisa save"));
                    container_hasil.revalidate();
                    container_hasil.repaint();
                }
            }
        });
        // HANDLER EVENT END

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
        container_control.add(hasil_outer, BorderLayout.CENTER);
        // hasil end

        // start menu
        JPanel startmenu = new JPanel(new GridBagLayout());
        JButton start = new JButton("Start");
        JButton load = new JButton("Load");
        JButton exit = new JButton("Exit");

        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                outerpane.remove(startmenu);
                outerpane.revalidate();
                outerpane.repaint();
            }
        });

        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    loadGame();
                    outerpane.remove(startmenu);
                    outerpane.revalidate();
                    outerpane.repaint();

                    pane.removeAll();
                    MapGrid m = new MapGrid(
                        gs.getPlayer().getPositionX(), gs.getPlayer().getPositionY(), gs.getMap());
                    pane.add(m, BorderLayout.WEST);
                    pane.revalidate();
                    pane.repaint();
                } catch(GameStateException ex){
                    System.exit(-1);
                }
            }
        });

        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        c.gridx = 0;
        c.gridy = 0;
        startmenu.add(new JLabel("Engimon Hunter 2000"));
        c.gridy++;
        startmenu.add(start, c);
        c.gridy++;
        startmenu.add(load, c);
        c.gridy++;
        startmenu.add(exit, c);

        pane.add(m, 1);
        innerBox.add(pane, BorderLayout.WEST);
        container_control.setSize(new Dimension(512, 480));
        container_control.add(control, BorderLayout.SOUTH);
        innerBox.add(container_control, BorderLayout.EAST);
        control.setSize(new Dimension(512, 90));
        container_control.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        outerpane.add(startmenu, 2);
        outerpane.add(innerBox, 1);

        outerpane.setSize(getSize());
        innerBox.setSize(getSize());
        startmenu.setSize(getSize());

        add(outerpane);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadGame() throws GameStateException{
        gs = GameState.load("data/save.dat");
    }
}

class MapGrid extends JPanel {
    private static final long serialVersionUID = 1L;
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
