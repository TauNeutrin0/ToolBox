package bots.toolbox;

import bots.StructuredBot;
import euphoria.FileIO;
import euphoria.RoomNotConnectedException;
import euphoria.events.MessageEvent;
import euphoria.events.MessageEventListener;
import euphoria.events.PacketEvent;
import euphoria.events.PacketEventListener;

public class TestGrandchild extends StructuredBot{
  FileIO dataFile;
  
  public TestGrandchild(StructuredBot parent) {
    super("TauGrandchild", parent, false);
    dataFile = new FileIO("ToolBox_data");
    useCookies(dataFile);
    addStandardEventListener("This bot is a secret! Shhh... \n In development by TauNeutrin0 and Sumairu.");
    connectRoom("bots");
    listeners.add(PacketEventListener.class, new MessageEventListener() {
      @Override
      public void onSendEvent(MessageEvent evt) {
        if(evt.getMessage().matches("^!collapse @"+TestGrandchild.this.getBotName()+"$")){
          synchronized(collapseLock){
            if(!TestGrandchild.this.isCollapsed(evt.getRoomConnection().getRoom())){
              reply("Collapsing...",evt);
              System.out.println("Collapsed gr");
              try {
                TestGrandchild.this.collapseBot(evt.getRoomConnection().getRoom());
              } catch (RoomNotConnectedException e) {
                e.printStackTrace();// Should never happen
              }
            }
          }
        } else if(evt.getMessage().matches("^!expand @"+TestGrandchild.this.getBotName()+"$")){
          synchronized(collapseLock){
            if(TestGrandchild.this.isCollapsed(evt.getRoomConnection().getRoom())){
              reply("Expanding...",evt);
              TestGrandchild.this.expandBot(evt.getRoomConnection().getRoom());
            }
          }
        } else if(evt.getMessage().matches("^!test @"+TestGrandchild.this.getBotName()+"$")){
          reply("Hello!",evt);
        }
      }
    });
  }
}
