package model.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import jakarta.servlet.ServletException;
import model.DAO;
import model.user.UserVO;

public class GameDAO extends DAO {

    public GameDAO(EntityManagerFactory emf) {
        super(emf);
    }
	
	@SuppressWarnings("unchecked")
	public ArrayList<GameVO> getGameByName(String name) {
		EntityManager em = emf.createEntityManager();
		ArrayList<GameVO> games = new ArrayList<GameVO>();

		try {
			Query query = em.createQuery("FROM " + GameVO.class.getName() + " WHERE name = :n");
			query.setParameter("n", name);
			games = (ArrayList<GameVO>) query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
        return games;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<GameVO> getAllGames() {
		EntityManager em = emf.createEntityManager();
		ArrayList<GameVO> games = new ArrayList<GameVO>();

		try {
			Query query = em.createQuery("FROM " + GameVO.class.getName());
			games = (ArrayList<GameVO>) query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
        return games;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<GameVO> getAllUserGames(UserVO user) {
		EntityManager em = emf.createEntityManager();
		ArrayList<GameVO> games = new ArrayList<GameVO>();
		
		try {
			Query query = em.createQuery("FROM " + GameVO.class.getName() + " WHERE user_id = :u");
			query.setParameter("u", user);
			games = (ArrayList<GameVO>) query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			em.close();
		}

		return games;
	}

    public boolean save(GameVO game) {
		EntityManager em = emf.createEntityManager();

        try {
			em.getTransaction().begin();
			em.persist(game);
			em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			em.close();
		}

    }

	public boolean delete(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			GameVO game = em.find(GameVO.class, id);
			em.getTransaction().begin();
			em.remove(game);
			em.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			em.close();

		}
	}

	public boolean update(int id, GameVO newGame) throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();

		try {
            newGame.setId(id);
			em.getTransaction().begin();
			em.merge(newGame);
			em.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			em.close();

		}
	}
	
    public boolean updateGameViews(int id) {
		EntityManager em = emf.createEntityManager();
		try {
  			//Primeira Tentativa
			GameVO game = em.find(GameVO.class, id);
			game.setViews(game.getViews()+1);
			em.getTransaction().begin();
			em.merge(game);
			em.getTransaction().commit();
			
			//Plano de contencao
//			Query query = em.createQuery("UPDATE " + GameVO.class.getName() + " SET views = :v WHERE id = :i");
//			query.setParameter("v", game.getViews()+1);
//			query.setParameter("i", id);
//			em.getTransaction().begin();
//			query.executeUpdate();
//			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
    }

	public GameVO getGameById(int gameId) {
		EntityManager em = emf.createEntityManager();
		
    	try {
    		return em.find(GameVO.class, gameId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	} finally {
			em.close();
    	}
	}
			
	public void buy(GameVO game) {
		EntityManager em = emf.createEntityManager();
		
    	try {
    		GameVO gameVo = em.find(GameVO.class, game.getId());
    		int newPurchases = gameVo.getPurchases() + 1;
    		gameVo.setPurchases(newPurchases);
			em.getTransaction().begin();
			em.merge(gameVo);
			em.getTransaction().commit();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return;
    	} finally {
			em.close();
    	}
	}
}
