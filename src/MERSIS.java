import java.rmi.RemoteException;

import customer.Customer;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;


public class MERSIS {

	public void isValidTC(Customer customer) throws NumberFormatException, RemoteException{
		
		KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
		
		boolean result = kpsPublic.TCKimlikNoDogrula(Long.parseLong(customer.getNationalityID())
				, customer.getFirstName(), customer.getLastName(), Integer.parseInt(customer.getDateOfBirth()));
		
		if(result){
			System.out.println("Dogrulama Basarili!");
		}else{
			System.out.println("Basarisiz!");
		}
	}
}
