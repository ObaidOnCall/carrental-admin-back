package ma.crm.carental.aspectj;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import ma.crm.carental.dtos.AssuranceRequestDto;
import ma.crm.carental.dtos.VehRequsetDto;
import ma.crm.carental.exception.UnableToProccessIteamException;
import ma.crm.carental.tenantfilter.TenantContext;


@Slf4j
@Aspect
@Component
public class VehiculeChecker {
    
	@PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* ma.crm.carental.services.VehiculeSerivce.saveVehs(..))")
	public void beforeAddVehicles(JoinPoint joinPoint) throws UnableToProccessIteamException{
		//Advice
		Object[] args = joinPoint.getArgs();
		@SuppressWarnings("unchecked")
		List<VehRequsetDto> vehrequests = (List<VehRequsetDto>) args[0];

		List <Long> models = vehrequests.stream()
											.map(veh -> veh.getModel())
											.collect(Collectors.toList()) ;

		String hql = "SELECT COUNT(m.id) " +
		"FROM Model m " +
		"WHERE m.tenantId = :tenantId " +
		"AND m.id IN :modelIds";

		Query query = entityManager.createQuery(hql) ;
		query.setParameter("tenantId", TenantContext.getTenantId()) ;
		query.setParameter("modelIds", models) ;
		long number =(long) query.getSingleResult() ;

		entityManager.clear();
		if (number != models.size()) {
			// All models are valid, proceed with vehicle creation
			throw new UnableToProccessIteamException("Unable to process the model item. Please check your request data") ;
		}
		
	}



	@Before("execution(* ma.crm.carental.services.VehiculeSerivce.saveAssurances(..))")
	public void beforeAddAssurances(JoinPoint joinPoint) throws UnableToProccessIteamException{
		//Advice
		Object[] args = joinPoint.getArgs();
		@SuppressWarnings("unchecked")
		List<AssuranceRequestDto> assuranceRequestDtos = (List<AssuranceRequestDto>) args[0];

		List <Long> vehiList = assuranceRequestDtos.stream()
											.map(assurance -> assurance.getVehicle())
											.collect(Collectors.toList()) ;

		String hql = "SELECT COUNT(v.id) " +
		"FROM Vehicule v " +
		"WHERE v.tenantId = :tenantId " +
		"AND v.id IN :vehiclesIds";

		Query query = entityManager.createQuery(hql) ;
		query.setParameter("tenantId", TenantContext.getTenantId()) ;
		query.setParameter("vehiclesIds", vehiList) ;
		long number =(long) query.getSingleResult() ;
		
		entityManager.clear();
		if (number != vehiList.size()) {
			// All models are valid, proceed with vehicle creation
			throw new UnableToProccessIteamException("Unable to process the Vehicle item. Please check your request data") ;
		}
		
	}
}
