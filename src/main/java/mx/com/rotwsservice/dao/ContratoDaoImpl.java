/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import mx.com.rotwsservice.dto.ErrorDto;
import mx.com.rotwsservice.model.Contrato;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mvillavicencio
 */
@Repository
public class ContratoDaoImpl implements ContratoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addContrato(Contrato contrato) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(contrato);
            return contrato.getIdContrato();
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Contrato getContratoById(Integer contratoId) {
        Contrato contrato = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Contrato.class);
            criteria.add(Restrictions.eq("idContrato", contratoId));
            contrato = (Contrato) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    @Override
    public ErrorDto checkIn(Contrato contrato, boolean esRoomie) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Contrato> criteria = builder.createCriteriaUpdate(Contrato.class);
        ErrorDto errorDto = new ErrorDto();
        try {
            criteria.from(Contrato.class);
            Root<Contrato> root = criteria.from(Contrato.class);
            if(esRoomie){
                criteria.set(root.get("roomieCheckin"),contrato.getRoomieCheckin());
                System.out.println("fecha : "+contrato.getRoomieCheckin());
                criteria.set(root.get("roomieCheckinSt"),contrato.getRoomieCheckinSt());
                System.out.println("Estatus : "+contrato.getRoomieCheckinSt());
                criteria.set(root.get("roomieCheckinComment"),contrato.getRoomieCheckinComment());
                System.out.println("Comentario : "+contrato.getRoomieCheckinComment());
                criteria.where(builder.equal(root.get("idContrato"), Integer.toString(contrato.getIdContrato())));
            }else{
                criteria.set(root.get("responsableCheckin"),contrato.getRoomieCheckin());
                criteria.set(root.get("responsableCheckinSt"),contrato.getRoomieCheckinSt());
                criteria.set(root.get("responsableCheckinComment"),contrato.getRoomieCheckinComment());
                criteria.where(builder.equal(root.get("idContrato"), Integer.toString(contrato.getIdContrato())));
            }
            session.createQuery(criteria).executeUpdate();
            errorDto.setNumeroError(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al realizar el checkin");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        } catch (Exception e) {
            e.printStackTrace();
            errorDto.setNumeroError(-1);
            errorDto.setError("Error al realizar el checkin");
            errorDto.setDescripcionError(e.getMessage());
            return errorDto;
        }
        return errorDto;
    }

    @Override
    public Contrato getCheckIn(Integer contratoId, boolean esRoomie) {
        
        Contrato contrato = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Contrato.class);
            if(esRoomie){
                criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("roomieCheckin"),"roomieCheckin")
                    .add(Projections.property("roomieCheckout"),"roomieCheckout")
                    .add(Projections.property("roomieCheckinSt"),"roomieCheckinSt")
                    .add(Projections.property("roomieCheckoutSt"),"roomieCheckoutSt")
                    .add(Projections.property("roomieCheckinComment"),"roomieCheckinComment")
                    .add(Projections.property("roomieCheckoutComment"),"roomieCheckoutComment"))
                    .setResultTransformer(Transformers.aliasToBean(Contrato.class));
            }else{
                criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("responsableCheckin"),"responsableCheckin")
                    .add(Projections.property("responsableCheckout"),"responsableCheckout")
                    .add(Projections.property("responsableCheckinSt"),"responsableCheckinSt")
                    .add(Projections.property("responsableCheckoutSt"),"responsableCheckoutSt")
                    .add(Projections.property("responsableCheckinComment"),"responsableCheckinComment")
                    .add(Projections.property("responsableCheckoutComment"),"responsableCheckoutComment"))
                    .setResultTransformer(Transformers.aliasToBean(Contrato.class));
            }
            criteria.add(Restrictions.eq("idContrato", contratoId));
            contrato = (Contrato) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return contrato;
    }
}
