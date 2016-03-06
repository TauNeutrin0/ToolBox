package bots;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import euphoria.Bot;
import euphoria.RoomConnection;
import euphoria.RoomNotConnectedException;
import euphoria.events.ConnectionEvent;
import euphoria.events.ConnectionEventListener;
import euphoria.events.MessageEvent;
import euphoria.events.PacketEvent;
import euphoria.events.PacketEventListener;
import euphoria.events.ReplyEventListener;
import euphoria.packets.StandardPacket;
import euphoria.packets.commands.Nick;
import euphoria.packets.events.SendEvent;
import euphoria.packets.replies.NickReply;

public abstract class StructuredBot extends Bot{
  private StructuredBot parent;
  private ArrayList<StructuredBot> children = new ArrayList<StructuredBot>();
  public ArrayList<String> collapsedRooms = new ArrayList<String>();
  public ArrayList<EventListenerList> collapsedRoomListeners = new ArrayList<EventListenerList>();
  private ArrayList<String> pausedRooms = new ArrayList<String>();
  public Object collapseLock = new Object();
  private String botName;
  
  public StructuredBot(String botName, StructuredBot parent, boolean hasConsole) {
    super(botName, hasConsole);
    this.botName = botName;
    switchParent(parent);
    listeners.add(PacketEventListener.class, new PacketEventListener() {
        @Override
        public void onBounceEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onBounceEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onBounceEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onHelloEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onHelloEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onHelloEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onJoinEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onJoinEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onJoinEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onNickEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onNickEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onNickEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onPartEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onPartEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onPartEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onSendEvent(MessageEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns1 = child.listeners.getListenerList();
                Object[] lns2 = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns1.length; i = i+2) {
                  if (lns1[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns1[i+1]).onSendEvent(evt);
                  }
                }
                for (int i = 0; i < lns2.length; i = i+2) {
                  if (lns2[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns2[i+1]).onSendEvent(evt);
                  }
                }
              }
            }
          }
        }
        @Override
        public void onSnapshotEvent(PacketEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            synchronized(child.collapseLock){
              if(child.isCollapsed(evt.getRoomConnection().getRoom())&&!child.isPaused(evt.getRoomConnection().getRoom())) {
                Object[] lns = child.listeners.getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onSnapshotEvent(evt);
                  }
                }
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
                for (int i = 0; i < lns.length; i = i+2) {
                  if (lns[i] == PacketEventListener.class) {
                    ((PacketEventListener) lns[i+1]).onSnapshotEvent(evt);
                  }
                }
              }
            }
          }
        }
    });
    listeners.add(ConnectionEventListener.class, new ConnectionEventListener() {
        @Override
        public void onConnect(ConnectionEvent evt) {}

        @Override
        public void onConnectionError(ConnectionEvent evt) {}

        @Override
        public void onDisconnect(ConnectionEvent evt) {
          for(int c=0;c<StructuredBot.this.children.size();c++) {
            StructuredBot child = StructuredBot.this.children.get(c);
            if(child.isCollapsed(evt.getRoomConnection().getRoom())){
              Object[] lns = child.listeners.getListenerList();
              for (int i = 0; i < lns.length; i = i+2) {
                if (lns[i] == PacketEventListener.class) {
                  ((ConnectionEventListener) lns[i+1]).onDisconnect(evt);
                }
              }
              synchronized(child.collapseLock){
                lns = child.collapsedRoomListeners.get(child.collapsedRooms.indexOf(evt.getRoomConnection().getRoom())).getListenerList();
              }
              for (int i = 0; i < lns.length; i = i+2) {
                if (lns[i] == PacketEventListener.class) {
                  ((ConnectionEventListener) lns[i+1]).onDisconnect(evt);
                }
              }
            }
          }
        }
      
    });
  }
  
  public void addStandardEventListener(String helpText) {
    final String help = helpText;
    listeners.add(PacketEventListener.class, new PacketEventListener() {
      @Override
      public void onSendEvent(MessageEvent evt) {
        if(evt.getMessage().matches("^!ping(?: @"+StructuredBot.this.botName+")?$")){
          reply("Pong!",evt);
        }
        if(evt.getMessage().matches("^!help @"+StructuredBot.this.botName+"$")){
          reply(help,evt);
        }
        if(evt.getMessage().matches("^!kill @"+StructuredBot.this.botName+"$")){
          reply("/me is now exiting.",evt);
          StructuredBot.this.disconnectRoom(evt.getRoomConnection().getRoom());
        }
        if(evt.getMessage().matches("^!pause @"+StructuredBot.this.botName+"$")){
          reply("/me has been paused.",evt);
          try {
            pause(evt.getRoomConnection().getRoom());
          } catch (RoomNotConnectedException e) {
              e.printStackTrace(); //Should never happen
          }
        }
      }
      @Override
      public void onSnapshotEvent(PacketEvent evt) {}
      public void onHelloEvent(PacketEvent evt) {
        evt.getRoomConnection().changeNick(StructuredBot.this.botName);
      }
      @Override
      public void onNickEvent(PacketEvent evt) {}
      @Override
      public void onJoinEvent(PacketEvent evt) {}
      @Override
      public void onPartEvent(PacketEvent evt) {}
      @Override
      public void onBounceEvent(PacketEvent arg0) {}
    });
  }
  
  public void addChild(StructuredBot child) {
    children.add(child);
  }
  
  public void removeChild(StructuredBot child) {
    children.remove(child);
  }
  
  public void switchParent(StructuredBot newParent) {
    if(parent!=null) parent.removeChild(this);
    parent = newParent;
    if(parent!=null) parent.addChild(this);
  }
  
  public void removeParent() {
    parent.removeChild(this);
    parent=null;
  }
  
  public void collapseChild(StructuredBot child, String room) throws RoomNotConnectedException {
    synchronized(child.collapseLock){
    if((isConnected(room)||isCollapsed(room))&&child.isConnected(room)){
        child.collapsedRooms.add(room);
        child.collapsedRoomListeners.add(child.getRoomConnection(room).listeners);
        final StructuredBot ch = child;
        final String rm = room;
        Thread t = new Thread(new Runnable() {
          public void run() {
            try {
                ch.getRoomConnection(rm).closeConnection("Collapsing bot...");
            } catch (RoomNotConnectedException e) {
                e.printStackTrace();
            }
          }
        });
        t.start();
      } else {
        throw new RoomNotConnectedException();
      }
    }
  }
  
  public void expandChild(StructuredBot child, String room) {
    synchronized(child.collapseLock){
      if(!child.isConnected(room)) {
        RoomConnection rmCon = child.createRoomConnection(room);
        int index = child.collapsedRooms.indexOf(room);
        rmCon.listeners=child.collapsedRoomListeners.get(index);
        startRoomConnection(rmCon);
        child.collapsedRoomListeners.remove(index);
        child.collapsedRooms.remove(index);
      }
    }
  }
  
  public void collapseBot(String room) throws RoomNotConnectedException {
    parent.collapseChild(this, room);
  }
  
  public void expandBot(String room) {
    parent.expandChild(this, room);
  }
  
  public void reply(String message, MessageEvent parentMsg) {
    sendPacket(((SendEvent)parentMsg.getPacket().getData()).createReply(message),parentMsg.getRoomConnection().getRoom());
  }
  public void trackedReply(String message, MessageEvent parentMsg, ReplyEventListener lst) {
    sendPacket(((SendEvent)parentMsg.getPacket().getData()).createReply(message),parentMsg.getRoomConnection().getRoom(),lst);
  }
  
  public void pause(String room) throws RoomNotConnectedException {
    if(collapsedRooms.contains(room)){
      pausedRooms.add(room);
    } else {
      getRoomConnection(room).pause(botName);
      pausedRooms.add(room);
    }
  }
  
  public void unPause(String room) throws RoomNotConnectedException{
    if(collapsedRooms.contains(room)){
      pausedRooms.remove(room);
    } else {
      getRoomConnection(room).unpause();
      pausedRooms.remove(room);
    }
    
  }
  
  public void sendPacket(StandardPacket pckt, String room) {
    final StandardPacket packet = pckt;
    final String rm = room;
    if(collapsedRooms.contains(room)) {
      if(pckt.getType().equals("send")) {
        parent.passPacket(new Nick(botName).createPacket(), room, new ReplyEventListener() { //Change parent nick to botName
          @Override
          public void onReplyEvent(PacketEvent evt) {
            final String oldName = ((NickReply)evt.getPacket().getData()).getFrom();
            StructuredBot.this.parent.passPacket(packet, rm, new ReplyEventListener() { //Send message
              @Override
              public void onReplyEvent(PacketEvent arg0) {
                parent.passPacket(new Nick(oldName).createPacket(), rm); //Change parent nick back to original
              }
              @Override
              public void onReplyFail(PacketEvent arg0) {}
              @Override
              public void onReplySuccess(PacketEvent arg0) {}
            });
          }
          @Override
          public void onReplyFail(PacketEvent arg0) {}
          @Override
          public void onReplySuccess(PacketEvent arg0) {}
        });
      } else {
        StructuredBot.this.parent.passPacket(pckt,room);
      }
    } else {
      try {
        this.getRoomConnection(room).sendServerMessage(pckt);
      } catch (RoomNotConnectedException e) {
          e.printStackTrace();
      }
    }
  }
  
  public void sendPacket(StandardPacket pckt, String room, ReplyEventListener lst) {
    final StandardPacket packet = pckt;
    final String rm = room;
    final ReplyEventListener listener = lst;
    if(collapsedRooms.contains(room)) {
      if(pckt.getType().equals("send")) {
        parent.passPacket(new Nick(botName).createPacket(), room, new ReplyEventListener() { //Change parent nick to botName
          @Override
          public void onReplyEvent(PacketEvent evt) {
            final String oldName = ((NickReply)evt.getPacket().getData()).getFrom();
            StructuredBot.this.parent.passPacket(packet, rm, new ReplyEventListener() { //Send message
              @Override
              public void onReplyEvent(PacketEvent arg0) {
                parent.passPacket(new Nick(oldName).createPacket(), rm); //Change parent nick back to original
                listener.onReplyEvent(arg0);
              }
              @Override
              public void onReplyFail(PacketEvent arg0) { listener.onReplyFail(arg0); }
              @Override
              public void onReplySuccess(PacketEvent arg0) { listener.onReplySuccess(arg0); }
            });
          }
          @Override
          public void onReplyFail(PacketEvent arg0) {}
          @Override
          public void onReplySuccess(PacketEvent arg0) {}
        });
      } else {
        StructuredBot.this.parent.passPacket(pckt,room,lst);
      }
    } else {
      try {
        this.getRoomConnection(room).sendTrackedMessage(pckt,lst);
      } catch (RoomNotConnectedException e) {
          e.printStackTrace();
      }
    }
  }
  
  public void passPacket(StandardPacket pckt, String room){
    if(collapsedRooms.contains(room)) {
      StructuredBot.this.parent.sendPacket(pckt,room);
    } else {
      try {
        this.getRoomConnection(room).sendServerMessage(pckt);
      } catch (RoomNotConnectedException e) {
          e.printStackTrace();
      }
    }
  }
  
  public void passPacket(StandardPacket pckt, String room, ReplyEventListener lst) {
    if(collapsedRooms.contains(room)) {
      StructuredBot.this.parent.sendPacket(pckt,room,lst);
    } else {
      try {
        this.getRoomConnection(room).sendTrackedMessage(pckt,lst);
      } catch (RoomNotConnectedException e) {
          e.printStackTrace();
      }
    }
  }
  
  public boolean isCollapsed(String room) { return collapsedRooms.contains(room); }
  public boolean isPaused(String room) { return pausedRooms.contains(room); }
  public String getBotName() { return botName; }
}
