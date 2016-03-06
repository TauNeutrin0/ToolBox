
import bots.toolbox.TestChild;
import bots.toolbox.TestGrandchild;
import bots.toolbox.TestParent;
import euphoria.FileIO;

public class ToolBox{
  FileIO dataFile;
  
  public ToolBox() {
    new TestGrandchild(new TestChild(new TestParent()));
  }
  
  public static void main(String[] args) {
    new ToolBox();
  }
}
