package com.neu.stepahead.dao;

import org.hibernate.Query;

import com.neu.stepahead.bean.Address;
import com.neu.stepahead.bean.User;

public class AddressDAO extends DAO {
	public AddressDAO() {

	}

	public int addAddress(Address address, long id) {
		int rowsUpdated = 0;
		begin();
		try {
			Query query = getSession().createQuery(
					"UPDATE Address ad SET ad.address1 = :address1, ad.address2 = :address2, ad.city = :city, ad.state = :state, ad.zipCode = :zipCode WHERE ad.addressId = :personId");
			query.setString("address1", address.getAddress1());
			query.setString("address2", address.getAddress2());
			query.setString("city", address.getCity());
			query.setString("state", address.getState());
			query.setString("zipCode", address.getZipCode());
			query.setLong("personId", id);
			rowsUpdated = query.executeUpdate();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return rowsUpdated;
	}
}
