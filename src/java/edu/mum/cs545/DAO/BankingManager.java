package edu.mum.cs545.DAO;

import edu.mum.cs545.model.*;
import java.util.Date;

public class BankingManager
{
	public static void main(String[] Args)
	{
		DAOFactory factory = DAOFactory.getFactory();
		CustomerDAO custDao = factory.getCustomerDAO();
		AccountDAO acctDao = factory.getAccountDAO();
                HistoryDAO histDao = factory.getHistoryDAO();
		
		custDao.beginTransaction();
		
		Customer cust1 = new Customer();
		cust1.setFirstName("Francis");
		cust1.setLastName("Joseph");
		cust1.setEmail("cesc.joseph@gmail.com");
		cust1.setZipCode(52557);
		cust1.setState("Iowa");
		
		Account acct = new Account();
		acct.setAccountNumber(new Long(147258));
		acct.setAccountType("CheckingAccount");
		acct.setBalance(10000.00);
		acct.setPIN(147852);
		acct.setCardNumber(137);
		//cust1.getAccounts().add(acct);
		acct.setCustomer(cust1);
                
                History transfer = new History();
                transfer.setTransferDate(new Date());
                transfer.setTransferAmount(5000);
                transfer.setAccountTo(147852);
                transfer.setAccount(acct);
		
		custDao.save(cust1);
		acctDao.save(acct);
                histDao.save(transfer);
		
		custDao.commitTransaction();
	}
}