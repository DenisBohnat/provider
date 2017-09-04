package by.epam.bohnat.provider.controller;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;

public class Main {

	public static void main(String[] args) {
		
		//List<Tariff> tariffList = new LinkedList<Tariff>();
		//List<Request> rList = new LinkedList<Request>();
		
		/*try {
			ConnectionPool pool=ConnectionPool.getInstance();
			pool.initConnectionPool();
			ServiceFactory f=ServiceFactory.getInstance();
			IRequestService s=f.getRequestService();
			rList=s.getAllRequests();
			//tariffList=s.getLimitedTariffs();
		} catch (ServiceException | ConnectionPoolException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<rList.size();i++){
			System.out.println(rList.get(i).toString());
		}*/
		/*try {
			ConnectionPool pool = ConnectionPool.getInstance();
			pool.initConnectionPool();
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			IUserService uService = serviceFactory.getUserService();
			Date birthDate = Date.valueOf("1988-02-02");
			Date regDate = Date.valueOf("2017-06-12");
			User user = new User(1000, "Name", "Surname", "Login", "Password", "test@mail.test", birthDate, regDate,
					"124578965", 1);
			uService.addUser(user);
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		long a= generateAccNumber();
		System.out.println(a);
		
		
		
		

	}

	private static long generateAccNumber(){
		StringBuilder accNumber = new StringBuilder();
		Random rand = new Random();
		for(int i=0;i<6;i++){
			int randomNumber = rand.nextInt(8)+1;
			accNumber.append(randomNumber);	
		}
		return Long.parseLong(accNumber.toString());
	}
	
}
