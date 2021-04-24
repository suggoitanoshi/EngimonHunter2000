package EngimonHunter2000;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
  public GUI(){
    setTitle("EngimonHunter2000");
    setSize(640, 480);
    JLayeredPane pane = new JLayeredPane();
    pane.setPreferredSize(new Dimension(640, 480));
    MainMenu menu = new MainMenu();
    MainGame game = new MainGame();
    pane.add(menu, 2);
    pane.add(game, 1);
    add(pane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  /*
  public static void main(String[] args){
    GUI g = new GUI();
    g.setVisible(true);
  }
  */
}

class MainGame extends JPanel{
  public MainGame(){
    JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    GameViz viz = new GameViz();
    JPanel move = new JPanel(new GridBagLayout());
    JButton moveUp = new JButton("w");
    JButton moveDown = new JButton("s");
    JButton moveLeft = new JButton("a");
    JButton moveRight = new JButton("d");
    JLabel catchPhrase = new JLabel("Smoke weed everyday");
    DefaultListModel<String> engimonInventoryModel = new DefaultListModel<String>();
    JList<String> engimonList = new JList<String>(engimonInventoryModel);
    JScrollPane engimonPane = new JScrollPane(engimonList);
    engimonPane.setPreferredSize(new Dimension(150, 200));
    JButton fight = new JButton("Gelud");
    JButton interact = new JButton("Interaksi");
    JButton change = new JButton("Ganti");
    JPanel engiButtons = new JPanel();
    EngimonDetails engiDetails = new EngimonDetails();
    DefaultListModel<String> itemInventoryModel = new DefaultListModel<String>();
    JList<String> itemInventoryList = new JList<String>(itemInventoryModel);
    JScrollPane itemPane = new JScrollPane(itemInventoryList);
    itemPane.setPreferredSize(new Dimension(150, 200));
    JButton useItem = new JButton("Pakai");

    engiButtons.add(fight);
    engiButtons.add(interact);
    engiButtons.add(change);

    c.fill = GridBagConstraints.BOTH;

    c.gridx = 1;
    c.gridy = 0;
    move.add(moveUp, c);
    c.gridx = 1;
    c.gridy = 1;
    move.add(moveDown, c);
    c.gridx = 2;
    move.add(moveRight, c);
    c.gridx = 0;
    move.add(moveLeft, c);

    c.gridx = 0;
    c.gridy = 0;
    pane.add(viz, c);
    c.gridy = 1;
    pane.add(move, c);
    c.gridy = 2;
    pane.add(catchPhrase, c);
    c.gridx = 1;
    c.gridy = 0;
    pane.add(engimonPane, c);
    c.gridy = 1;
    pane.add(engiButtons, c);
    c.gridy = 2;
    pane.add(engiDetails, c);
    c.gridx = 2;
    c.gridy = 0;
    pane.add(itemPane, c);
    c.gridy = 1;
    pane.add(useItem, c);
    add(pane);
    setSize(new Dimension(640, 480));
  }
}

class EngimonDetails extends JPanel{
  private JLabel nama, live, parent1, parent2, species, elements, skills, level, exp, cexp;

  public EngimonDetails(){
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    // nama, live, parentName, speciesName, elements, skills, level, exp, cex
    nama = new JLabel("Nama");
    live = new JLabel("Live");
    parent1 = new JLabel("Parent 1");
    parent2 = new JLabel("Parent 2");
    species = new JLabel("Species");
    elements = new JLabel("Elements");
    skills = new JLabel("Skills");
    level = new JLabel("Level");
    exp = new JLabel("Exp");
    cexp = new JLabel("Cumulative Exp");

    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    panel.add(nama, c);
    c.gridx = 0;
    c.gridy = 1;
    panel.add(live, c);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    panel.add(parent1, c);
    c.gridx = 1;
    panel.add(parent2, c);
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 2;
    panel.add(species, c);
    c.gridy = 4;
    panel.add(elements, c);
    c.gridy = 5;
    panel.add(skills, c);
    c.gridy = 6;
    panel.add(level, c);
    c.gridy = 7;
    panel.add(exp, c);
    c.gridy = 8;
    panel.add(cexp, c);
    add(panel);
    setPreferredSize(new Dimension(150, 200));
  }

  /* TODO: Impleemnt Engimon!
  public void SetEngimon(Engimon newE){
    this.nama.setText(newE.getName());
    this.live.setText("Lives: "+newE.getLives());
    Engimon[] parents = newE.getParents();
    this.parent1.setText("Parent1: "+parents[0]);
    this.parent2.setText("Parent2: "+parents[1]);
    this.species.setText("Species: "+newE.getSpecies());
    this.elements.setText("Element: "+newE.getElements().toString());
    this.skills.setText("Skills: "+newE.getSkills());
    this.level.setText("Level: "+newE.getLevel());
    this.exp.setText("Exp: "+newE.getExp());
    this.cexp.setText("CExp: "+newE.getCExp());
  }
  */
}

class MainMenu extends JPanel{
  public MainMenu(){
    JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JButton start = new JButton("Start");
    JButton load = new JButton("Save");

    start.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });

    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 0;
    pane.add(start, c);
    c.gridy = 2;
    pane.add(load, c);
    add(pane, BorderLayout.CENTER);
    setSize(640, 480);
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(640, 480);
  }
}

class GameViz extends JPanel{
  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.drawPolygon(new int[]{0, 140, 140, 0}, new int[]{0, 0, 140, 140}, 4);
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(200, 200);
  }
}

class Loader implements Runnable{
  @Override
  public void run(){
    try{
      Thread.sleep(1000);
      System.out.println("run");
    }
    catch(Exception e){}
  }
}