package web.oficina.repository.helper.manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import web.oficina.model.Manutencao;
import web.oficina.model.StatusManutencao;
import web.oficina.model.Usuario;
import web.oficina.repository.pagination.PaginacaoUtil;

public class ManutencaoQueriesImpl implements ManutencaoQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Manutencao> pesquisar(Pageable pageable) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Manutencao> criteriaQuery = builder.createQuery(Manutencao.class);
		Root<Manutencao> m = criteriaQuery.from(Manutencao.class);
		TypedQuery<Manutencao> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();
		m.fetch("usuario", JoinType.INNER);
		m.fetch("equipamento", JoinType.INNER);

		predicateList.add(builder.equal(m.<Usuario>get("usuario").<String>get("login"), currentPrincipalName));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(m).where(predArray);
		PaginacaoUtil.prepararOrdem(m, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Manutencao> manutencoes = typedQuery.getResultList();

		long totalManutencoes = PaginacaoUtil.getTotalRegistros(m, predArray, builder, manager);

		Page<Manutencao> page = new PageImpl<>(manutencoes, pageable, totalManutencoes);

		return page;
	}

}