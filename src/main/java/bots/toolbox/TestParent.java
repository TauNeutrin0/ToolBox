package bots.toolbox;

import bots.StructuredBot;
import euphoria.FileIO;
import euphoria.events.MessageEvent;
import euphoria.events.MessageEventListener;
import euphoria.events.PacketEventListener;

public class TestParent extends StructuredBot{
  FileIO dataFile;
  public TestParent() {
    super("TauParent", null, true);
    dataFile = new FileIO("ToolBox_data");
    useCookies(dataFile);
    addStandardEventListener("This bot is a secret! Shhh... \n In development by TauNeutrin0 and Sumairu.");
    connectRoom("bots");
    listeners.add(PacketEventListener.class, new MessageEventListener() {
        @Override
        public void onSendEvent(MessageEvent evt) {
          if(evt.getMessage().matches("^!test @"+TestParent.this.getBotName()+"$")){
            reply("Hello!",evt);
          }
        }
    });
  }
  
}
