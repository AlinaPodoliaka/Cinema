package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> cq = cb.createQuery(MovieSession.class);
            Root<MovieSession> root = cq.from(MovieSession.class);
            Predicate predicateId = cb.equal(root.get("movie"), movieId);
            Predicate predicateDate = cb.between(root.get("showTime"),
                    date.atStartOfDay(), date.plusDays(1).atStartOfDay());
            cq.select(root).where(cb.and(predicateId, predicateDate));
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while finding available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession session) {
        Transaction transaction = null;
        try (Session sessionH = HibernateUtil.getSessionFactory().openSession()) {
            transaction = sessionH.beginTransaction();
            Long itemId = (Long) sessionH.save(session);
            transaction.commit();
            session.setId(itemId);
            return session;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie session entity", e);
        }
    }
}
