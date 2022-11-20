package web.oficina.repository.helper.equipamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import web.oficina.model.Equipamento;
import web.oficina.model.StatusEquipamento;
import web.oficina.model.filter.EquipamentoFilter;
import web.oficina.repository.pagination.PaginacaoUtil;

public class EquipamentoQueriesImpl implements EquipamentoQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Equipamento> pesquisar(EquipamentoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Equipamento> criteriaQuery = builder.createQuery(Equipamento.class);
		Root<Equipamento> e = criteriaQuery.from(Equipamento.class);
		TypedQuery<Equipamento> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();
		
		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(e.<Long>get("codigo"),
					filtro.getCodigo()));
		}
		
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(	builder.lower(e.<String>get("nome")),
										"%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		if (StringUtils.hasText(filtro.getMarca())) {
			predicateList.add(builder.like(	builder.lower(e.<String>get("marca")),
										"%" + filtro.getMarca().toLowerCase() + "%"));
		}

		predicateList.add(builder.equal(e.<StatusEquipamento>get("status"), StatusEquipamento.DISPONIVEL));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(e).where(predArray);
		PaginacaoUtil.prepararOrdem(e, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Equipamento> equipamentos = typedQuery.getResultList();

		long totalEquipamentos = PaginacaoUtil.getTotalRegistros(e, predArray, builder, manager);

		Page<Equipamento> page = new PageImpl<>(equipamentos, pageable, totalEquipamentos);

		return page;
	}

}