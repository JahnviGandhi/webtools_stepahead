package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Query;

import com.neu.stepahead.bean.Position;

public class PositionDAO extends DAO {
	public PositionDAO() {

	}

	public List<Position> getPositions() {
		List<Position> positions = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Position p");
			positions = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return positions;
	}

	public Position getPositionByName(String posTitle) {
		Position position = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Position p WHERE p.positionTitle = :positionTitle");
			query.setString("positionTitle", posTitle);
			position = (Position) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return position;
	}

	public long addPosition(Position position) {
		long positionId = 0;
		begin();

		try {
			getSession().saveOrUpdate(position);
			positionId = position.getPositionId();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return positionId;
	}

	public int deletePosition(long positionId) {
		int result = 0;

		try {
			Query query = getSession().createQuery("DELETE FROM Position p WHERE p.positionId = :positionId");
			query.setLong("positionId", positionId);
			result = query.executeUpdate();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return result;
	}

	public Position getPositionById(long positionId) {
		Position pos = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Position p WHERE p.positionId = :positionId");
			query.setLong("positionId", positionId);
			pos = (Position) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - getPositionById: " + ex.getMessage());
		} finally {
			close();
		}
		return pos;
	}
}
