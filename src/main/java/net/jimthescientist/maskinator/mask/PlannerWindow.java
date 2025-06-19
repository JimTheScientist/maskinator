package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.Wafer;
import net.jimthescientist.maskinator.outputmachine.OutputMachine;
import net.jimthescientist.maskinator.outputmachine.Projector;
import net.jimthescientist.maskinator.outputmachine.ResinPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlannerWindow extends JFrame {
    private JPanel outputPanel;
    private final JFrame plannerFrame;
    private Wafer wafer = new Wafer();
    private Planner planner;
    public PlannerWindow() {
        Main.LOGGER.info("Opening new Planner window");
        plannerFrame = new JFrame("Planner");
        plannerFrame.getContentPane().setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu outputDevicesMenu = new JMenu("Output Devices");
        JMenuItem resinPrinter = new JMenuItem("Resin Printer");

        resinPrinter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ResinPrinter resinPrinter = new ResinPrinter();
                setOutputPanel(resinPrinter);
            }
        });

        outputDevicesMenu.add(resinPrinter);

        JMenuItem projectorItem = new JMenuItem("Projector");
        projectorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Projector projector = new Projector();
                setOutputPanel(projector);
            }
        });

        outputDevicesMenu.add(projectorItem);

        menuBar.add(outputDevicesMenu);

        plannerFrame.setJMenuBar(menuBar);

        planner = new Planner();
        setWaferPanel();
        plannerFrame.getContentPane().add(planner, BorderLayout.CENTER);
        plannerFrame.setSize(800, 600);
        plannerFrame.setVisible(true);
    }

    public void setOutputPanel(OutputMachine outputMachine) {
        Main.LOGGER.info("Setting output panel.");
        if (outputPanel != null) { plannerFrame.remove(this.outputPanel);}
        this.outputPanel = outputMachine.getPanel();
        plannerFrame.getContentPane().add(this.outputPanel, BorderLayout.EAST);
        this.planner.getGrid().setOutputMachine(outputMachine);
        plannerFrame.getContentPane().revalidate();
    }

    public void setWaferPanel() {
        planner.getGrid().setWafer(wafer);
        plannerFrame.getContentPane().add(wafer.getWaferSettings(), BorderLayout.WEST);
    }
}
