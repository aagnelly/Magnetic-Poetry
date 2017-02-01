import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import javax.swing.*;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DragAndDrop extends JFrame implements ActionListener {
    String[] poetry = {"the", "of", "and", "a","to", "in", "at", "heard", "he", "she", "heard", 
        "time", "as", "no", "yes", "make", "saw", "has", "said", "think", "love", "hate", "was",
        "also", "sun", "moon", "around", "red", "me", "you", "give", "an", "without", "once", "girl",
        "boy", "would", "sea", "mountains", "don't", "light", "study", "big", "want", "food", "upon",
        "catz", "internet", ".\n", "//\n" };
    JLabel[] labels = new JLabel[poetry.length];
    JButton clear = new JButton("Clear words");
   
    DropTargetTextArea textArea = new DropTargetTextArea();      //text area to drop text
    
    public DragAndDrop(){
    JFrame frame = new JFrame("Magnetic Poetry");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WIDTH, HEIGHT);
    JPanel panel = new JPanel();
        
    
        
    // Add a drop target text area to frame
    frame.add(textArea, BorderLayout.CENTER);
    frame.add(clear, BorderLayout.NORTH);
    
    clear.addActionListener(this);
    
    panel.setLayout(new GridLayout(4, 10, 4, 3));
    
    // Add  draggable words to the container
    for (int i =0; i < poetry.length; i++){
        labels[i] = new DragLabel(poetry[i]);
        panel.add(labels[i]);
    
        //add panel labels
        frame.add(panel, BorderLayout.SOUTH);
    }
    
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    frame.pack();
    frame.setVisible(true);
    }
    
    
    
    
  public static void main(String[] args) {

    DragAndDrop gui = new DragAndDrop();
  }

    @Override
    public void actionPerformed(ActionEvent ae) {
        textArea.clearText();
    }

}//end class DragAndDrop
    
    
  
  // Make a Label draggable
 class DragLabel extends JLabel implements DragGestureListener, DragSourceListener {
    DragSource dragSource;
  
    //label constructor
    public DragLabel(String s) {
        setText(s);
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, (DragGestureListener) this);
        }


    @Override
    public void dragGestureRecognized(DragGestureEvent evt) {
        Transferable transferable = new StringSelection(getText());
        dragSource.startDrag(evt, DragSource.DefaultCopyDrop, transferable, this);
    }

    // Implement abstract methods
    @Override
    public void dragEnter(DragSourceDragEvent evt) {
    }

    @Override
    public void dragOver(DragSourceDragEvent evt) {
    }

    @Override
    public void dragExit(DragSourceEvent evt) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent evt) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent evt) {
    }

  }// end class DragLabel



  // Make a TextArea the drop target
    class DropTargetTextArea extends TextArea implements DropTargetListener {

        public DropTargetTextArea() {
            new DropTarget(this, this);
        }

        // Implement abstract methods
        @Override
        public void dragEnter(DropTargetDragEvent evt) {
        }

        @Override
        public void dragOver(DropTargetDragEvent evt) {
        }

        @Override
        public void dragExit(DropTargetEvent evt) {
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent evt) {
        }

        @Override
        public void drop(DropTargetDropEvent evt) {
        try {

        Transferable transferable = evt.getTransferable();

            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                evt.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                String dragContents = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                evt.getDropTargetContext().dropComplete(true);

                // Append the label text to the text area when dropped
                setText(getText() + " " + dragContents);
                } else {
                evt.rejectDrop();
                  }
                } catch (IOException e) {
                  evt.rejectDrop();
                } catch (UnsupportedFlavorException e) {
                evt.rejectDrop();
                }
        
 
        }
       void clearText(){
            setText("");
    }

  }//end class DropTargetTextArea

