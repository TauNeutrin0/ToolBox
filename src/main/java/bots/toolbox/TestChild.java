package bots.toolbox;

import bots.StructuredBot;
import euphoria.FileIO;
import euphoria.RoomNotConnectedException;
import euphoria.events.MessageEvent;
import euphoria.events.MessageEventListener;
import euphoria.events.PacketEvent;
import euphoria.events.PacketEventListener;

public class TestChild extends StructuredBot{
  FileIO dataFile;
  
  public TestChild(StructuredBot parent) {
    super("TauChild", parent, false);
    dataFile = new FileIO("ToolBox_data");
    useCookies(dataFile);
    addStandardEventListener("This bot is a secret! Shhh... \n In development by TauNeutrin0 and Sumairu.");
    connectRoom("bots");
    listeners.add(PacketEventListener.class, new MessageEventListener() {
        @Override
        public void onSendEvent(MessageEvent evt) {
          if(evt.getMessage().matches("^!collapse @"+TestChild.this.getBotName()+"$")){
            synchronized(collapseLock){
              if(!TestChild.this.isCollapsed(evt.getRoomConnection().getRoom())){
                reply("Collapsing...",evt);
                try {
                  TestChild.this.collapseBot(evt.getRoomConnection().getRoom());
                } catch (RoomNotConnectedException e) {
                  e.printStackTrace();// Should never happen
                }
              }
            }
          } else if(evt.getMessage().matches("^!expand @"+TestChild.this.getBotName()+"$")){
            synchronized(collapseLock){
              if(TestChild.this.isCollapsed(evt.getRoomConnection().getRoom())){
                reply("Expanding...",evt);
                TestChild.this.expandBot(evt.getRoomConnection().getRoom());
              }
            }
          } else if(evt.getMessage().matches("^!test @"+TestChild.this.getBotName()+"$")){
            reply("Hello!",evt);
          }
        }
    });
  }
}
