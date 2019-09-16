import java.rmi.*;
public interface ServiceServer extends Remote{
	Object[] getServiceList() throws RemoteException;
	Service getSevice(Object serviceKey) throws RemoteException;
}