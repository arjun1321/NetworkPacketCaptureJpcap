
package jpcapproject;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.JpcapWriter;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.Packet;

/**
 *
 * @author Arjun Kumar
 */
public class JpCapProject {

    public static void main(String[] args) throws IOException {
        
        // Obtain the list of network devices
        jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        
        // for each network interface
        for (int i=0; i<devices.length; i++) {
            
            // Print out its name and description
            System.out.println(i + ": " + devices[i].name + "(" + devices[i].description + ")");
            
            // Print out its datalink name and description
            System.out.println("datalink: " + devices[i].datalink_name + "(" + devices[i].datalink_description + ")");
            
            // Print out its MAC address
            System.out.print("MAC address:");
            for (byte b: devices[i].mac_address)
                System.out.print(Integer.toHexString(b&0xff) + ":");
            System.out.println();
            
            // Print out its Ip address, subnet mask and broadcast address
            for (NetworkInterfaceAddress a : devices[i].addresses)
                System.out.println(" address:" + a.address + " " + a.subnet + " " + a.broadcast);   
        }
        
        // Opening the network interface
            JpcapCaptor captor = null;
 /*           try {
                captor = JpcapCaptor.openDevice(devices[3], 65535, false, 20);
            } catch (IOException ex) {
                Logger.getLogger(JpCapProject.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //open a file to save captured packets
            
            JpcapWriter writer=JpcapWriter.openDumpFile(captor,"CapturedPackets");

            // Capturing the packet one by one
            for(int j=0; j<10; j++) {
                //capture a single packet and print it out
                if(captor == null)
                    break;
//                System.out.println(captor.getPacket());
                writer.writePacket(captor.getPacket());
                
            }

            writer.close();
            captor.close();
*/ 
 
        
            System.out.println();
            System.out.println();
            //open a file to read saved packets
            captor=JpcapCaptor.openFile("CapturedPackets");

            while(true){
              //read a packet from the opened file
              Packet packet=captor.getPacket();
              //if some error occurred or EOF has reached, break the loop
              if(packet==null || packet==Packet.EOF) break;
              //otherwise, print out the packet
              System.out.println(packet);
            }

            captor.close();

            
        
    }
    
}
