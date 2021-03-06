package company.pos.util;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
