package ma.crm.carental.aspectj;

import java.util.List;
import java.util.Objects;
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
import ma.crm.carental.dtos.ModelRequestDto;
import ma.crm.carental.dtos.VehRequsetDto;
import ma.crm.carental.exception.UnableToProccessIteamException;
import ma.crm.carental.tenantfilter.TenantContext;


@Slf4j
@Aspect
@Component
public class VehiculeChecker {


	private static final String ERRORMESSAGE = "access denied or unable to process the item ,please use a valid data" ;

	@PersistenceContext
    private EntityManager entityManager;

	@Before("@annotation(ma.crm.carental.annotations.ValidateVehiculeModels)")
	public void validateVehicleModels(JoinPoint joinPoint) throws UnableToProccessIteamException{
		Object[] args = joinPoint.getArgs();

    	// Assuming the first argument is always your DTO
		if (args.length > 0 && args[0] instanceof List) {
				@SuppressWarnings("unchecked")
				List<VehRequsetDto> vehrequests = (List<VehRequsetDto>) args[0];

				List<Long> models = vehrequests.stream()
						.map(VehRequsetDto::getModel)
						.filter(Objects::nonNull) // Filter out null models
						.collect(Collectors.toList());

				// Only validate if there are models to check
				if (!models.isEmpty()) {
					validateModels(models);
				}
		}

		
	}


	@Before("@annotation(ma.crm.carental.annotations.ValidateVehiculeBrands)")
	public void validateVehicleBrands(JoinPoint joinPoint) throws UnableToProccessIteamException{
		Object[] args = joinPoint.getArgs();

    	// Assuming the first argument is always your DTO
		if (args.length > 0 && args[0] instanceof List) {
				@SuppressWarnings("unchecked")
				List<ModelRequestDto> modelRequestDtos = (List<ModelRequestDto>) args[0];

				List<Long> brands = modelRequestDtos.stream()
						.map(ModelRequestDto::getBrand)
						.filter(Objects::nonNull) // Filter out null models
						.collect(Collectors.toList());

				// Only validate if there are models to check
				if (!brands.isEmpty()) {
					validateBrands(brands);
				}
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
			throw new UnableToProccessIteamException(VehiculeChecker.ERRORMESSAGE) ;
		}
		
	}





	private void validateModels(List<Long> models) throws UnableToProccessIteamException {
		String hql = "SELECT COUNT(m.id) FROM Model m WHERE m.tenantId = :tenantId AND m.id IN :modelIds";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("tenantId", TenantContext.getTenantId());
		query.setParameter("modelIds", models);
		long number = (long) query.getSingleResult();
		
		entityManager.clear();
		
		if (number != models.size()) {
			throw new UnableToProccessIteamException(VehiculeChecker.ERRORMESSAGE);
		}
	}


	private void validateBrands(List<Long> brands) throws UnableToProccessIteamException {
		String hql = "SELECT COUNT(b.id) FROM Brand b WHERE b.tenantId = :tenantId AND b.id IN :brandIds";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("tenantId", TenantContext.getTenantId());
		query.setParameter("brandIds", brands);
		long number = (long) query.getSingleResult();
		
		entityManager.clear();
		
		if (number != brands.size()) {
			throw new UnableToProccessIteamException(VehiculeChecker.ERRORMESSAGE);
		}
	}

}
