/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pincipal;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ClipboardWindows {
    public static void copy(String text) {
        Clipboard clipboard = getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
    }

    public static void paste() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
    }

    public static String get() throws Exception {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
            Object text = systemClipboard.getData(dataFlavor);
            return (String) text;
        }

        return null;
    }   
    
    public static void AddPortapapelesContend(String pegado){
        StringSelection ss = new StringSelection(pegado);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);  
    }
    
    public static String copiarPortapapeles() throws UnsupportedFlavorException, IOException {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String copiado = (String) t.getTransferData(DataFlavor.stringFlavor);
        return copiado;
    }
    
    
    //--------------------------------------------------------------------------
    private static Clipboard getSystemClipboard(){
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
}