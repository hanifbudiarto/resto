/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Muhammad Hanif B
 */
public class FrameUtil {
    public static JFrame currentFrame;

    /**
     *
     * @return
     */
    public static JFrame getCurrentFrame() {
        return currentFrame;
    }

    /**
     *
     * @param currentFrame
     */
    private static void setCurrentFrame(JFrame currentFrame) {
        FrameUtil.currentFrame = currentFrame;
    }
    
    private static void resetCurrentFrame (JPanel panel) {
        currentFrame.setContentPane(panel);
        currentFrame.pack();
        currentFrame.revalidate();
        currentFrame.repaint();
        currentFrame.setLocationRelativeTo(null);
    }
    
    public static void changeUI (JPanel panel, JFrame frame) {
        setCurrentFrame(frame);
        resetCurrentFrame(panel);
    }
    
}
