package web.oficina.repository.helper.usuario;

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

import web.oficina.model.Usuario;
import web.oficina.model.StatusUsuario;
import web.oficina.model.filter.UsuarioFilter;
import web.oficina.repository.pagination.PaginacaoUtil;

public class UsuarioQueriesImpl implements UsuarioQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		Root<Usuario> u = criteriaQuery.from(Usuario.class);
		TypedQuery<Usuario> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();
		
		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(u.<Long>get("codigo"),
					filtro.getCodigo()));
		}
		
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(	builder.lower(u.<String>get("nome")),
										"%" + filtro.getNome().toLowerCase() + "%"));
		}

		predicateList.add(builder.equal(u.<StatusUsuario>get("status"), StatusUsuario.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(u).where(predArray);
		PaginacaoUtil.prepararOrdem(u, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Usuario> usuarios = typedQuery.getResultList();

		long totalUsuarios = PaginacaoUtil.getTotalRegistros(u, predArray, builder, manager);

		Page<Usuario> page = new PageImpl<>(usuarios, pageable, totalUsuarios);

		return page;
	}

}