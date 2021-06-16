package data;

import javax.swing.*;
import java.awt.*;

public class Frame {

    JFrame frame;
    JPanel panelCont, panelStart, panelAns, panelGT, panelSyn;
    JButton buttonAns, buttonGT, buttonSyn, buttonSendAns, buttonSendGT, buttonSendSyn;

    JTextField jtfAnsCaseID, jtfAnsKeywords, jtfAnsAnswer;
    JLabel jlAnsCaseID, jlAnsKeywords, jlAnsAnswer;
    JTextField jtfGTID, jtfGTGenericTerm;
    JLabel jlGTID, jlGTGenericTerm;
    JTextField jtfSynSynonym, jtfSynID;
    JLabel jlSynSynonym, jlSynID;

    CardLayout cl;

    // TODO: add Dimensions for each textfield
    Frame() {
        frame = new JFrame("naoImporter");
        panelCont = new JPanel();
        cl = new CardLayout();
        panelCont.setLayout(cl);

        //Initialize components for start
        panelStart = new JPanel();
        buttonAns = new JButton("Answer");
        buttonGT = new JButton("Generic terms");
        buttonSyn = new JButton("Synonyms");

        //Initialize components for answers
        panelAns = new JPanel();
        jlAnsCaseID = new JLabel("CaseID:");
        jtfAnsCaseID = new JTextField();
        jlAnsKeywords = new JLabel("Keywords:");
        jtfAnsKeywords = new JTextField();
        jlAnsAnswer = new JLabel("Answer:");
        jtfAnsAnswer = new JTextField();
        buttonSendAns = new JButton("Send Data!");

        //Initialize components for generic terms
        panelGT = new JPanel();
        jlGTID = new JLabel("ID");
        jtfGTID = new JTextField();
        jlGTGenericTerm = new JLabel("Generic term:");
        jtfGTGenericTerm = new JTextField();
        buttonSendGT = new JButton("Send Data!");

        //Initialize components for synonyms
        panelSyn = new JPanel();
        jlSynSynonym = new JLabel("Synonym:");
        jtfSynSynonym = new JTextField();
        jlSynID = new JLabel("ID:");
        jtfSynID = new JTextField();
        buttonSendSyn = new JButton("Send Data!");

        //Configuring each component
        panelStart.setLayout(new FlowLayout());
        panelAns.setLayout(new FlowLayout());
        panelGT.setLayout(new FlowLayout());
        panelSyn.setLayout(new FlowLayout());


        //Button listener logic
        buttonAns.addActionListener(e -> cl.show(panelCont, "2"));

        buttonGT.addActionListener(e -> cl.show(panelCont, "3"));

        buttonSyn.addActionListener(e -> cl.show(panelCont, "4"));

        //Adding component ot each panel
        panelStart.add(buttonAns);
        panelStart.add(buttonGT);
        panelStart.add(buttonSyn);

        panelAns.add(jlAnsCaseID);
        panelAns.add(jtfAnsCaseID);
        panelAns.add(jlAnsKeywords);
        panelAns.add(jtfAnsKeywords);
        panelAns.add(jlAnsAnswer);
        panelAns.add(jtfAnsAnswer);
        panelAns.add(buttonSendAns);

        panelGT.add(jlGTID);
        panelGT.add(jtfGTID);
        panelGT.add(jlGTGenericTerm);
        panelGT.add(jtfGTGenericTerm);
        panelGT.add(buttonSendGT);

        panelSyn.add(jlSynSynonym);
        panelSyn.add(jtfSynSynonym);
        panelSyn.add(jlSynID);
        panelSyn.add(jtfSynID);
        panelSyn.add(buttonSendSyn);

        panelCont.add(panelStart, "1");
        panelCont.add(panelAns, "2");
        panelCont.add(panelGT, "3");
        panelCont.add(panelSyn, "4");


        cl.show(panelCont, "1");

        frame.add(panelCont);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
