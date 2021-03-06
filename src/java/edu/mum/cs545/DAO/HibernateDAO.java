package edu.mum.cs545.DAO;

import java.util.*;
import java.io.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import edu.mum.cs545.HibernateUtil;

public abstract class HibernateDAO< T , ID extends Serializable> implements GenericDAO<T, ID>
{
	private Class<T> persistenceClass;
	
	public HibernateDAO(Class c)
	{
		this.persistenceClass = c;
	}	
	
	public T findByPrimaryKey(ID id)
	{
		return (T) HibernateUtil.getSession().load(persistenceClass, id);
	}
	
	public List<T> findByExample(T exampleInstance, String[] excludeProperty)
	{
		Criteria crit = HibernateUtil.getSession().createCriteria(persistenceClass);
		Example example = Example.create(exampleInstance);
		if(excludeProperty != null)
		{
			for(int i = 0; i < excludeProperty.length; i ++)
			{
				example.excludeProperty(excludeProperty[i]);
			}
		}
		crit.add(example);
		return crit.list();
	}
	
	public List<T> findAll(int startIndex, int fetchSize)
	{
		Criteria crit = HibernateUtil.getSession().createCriteria(persistenceClass);
		crit.setFirstResult(startIndex);
		crit.setFetchSize(fetchSize);
		return crit.list();
	}
	
	public T save(T entity)
	{
		HibernateUtil.getSession().saveOrUpdate(entity);
		return entity;
	}
	
	public void delete(T entity)
	{
		HibernateUtil.getSession().delete(entity);
	}
	
	public void beginTransaction() { HibernateUtil.beginTransaction(); }
	public void commitTransaction() { HibernateUtil.commitTransaction(); }
}