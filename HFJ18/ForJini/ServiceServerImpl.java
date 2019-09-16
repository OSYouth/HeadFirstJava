//imcomplecated
import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer{
	HashMap serviceList;
	public ServiceServerImpl() throws RemoteException{
		serUpServices();
	}
	private void serUpServices(){
		serviceList = new HashMap();
		serviceList.put("Dice Rolling Service", new DiceService());
		serviceList.put("Day of the Week Service", new DayOfTheWeekService());
		serviceList.put("Visual Music Service", new MiniMusicServive());
	}
	public Object[] getServiceList(){
		System.out.println("in remote");
		return serviceList.keySet().toArray();
	}
	public Service gerService(Object serviceKey) throws RemoteException{
		Service theService = (Service) serviceList.get(serviceKey);
		return theService;
	}

	public static void main(String[] args){
		try{
			Naming.rebind("ServiceServer", new ServiceServerImpl());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Remote Service is running.");
	}
}