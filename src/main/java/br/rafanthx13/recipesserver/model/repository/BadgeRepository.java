package br.rafanthx13.recipesserver.model.repository;

import br.rafanthx13.recipesserver.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    // @Query(name = "SELECT b.id, b.tag\n" +
    //         "FROM recipe r\n" +
    //         "LEFT JOIN recipe_badge rb ON r.id = rb.recipe_id\n" +
    //         "LEFT JOIN badge b ON b.id = rb.badge_id\n" +
    //         "WHERE r.id = :id_recipe;", nativeQuery = true)
    // List<Badge> join( @Param("id_recipe") Long recipeId);
// :name
  /*
  // True :: Livro Está emprestado; False: Livro Está disponível
	// Eu conto a quantidade de linhas que tenham o livro e returned é Null/False
    @Query(value = " select case when ( count(l.id) > 0 ) then true else false end " +
            " from Loan l where l.book = :book and ( l.returned is null or l.returned is false ) ")
    boolean existsByBookAndNotReturned( @Param("book") Book book );

    // GET Filted
    // Perceba que '@Param("isbn")' vai ser mapeado par aum dado mesmo na consulta SQL
    // Mandar Pageable vai automaticamente voltar pagiado
    @Query( value = " select l from Loan as l join l.book as b where b.isbn = :isbn or l.customer =:customer ")
    Page<Loan> findByBookIsbnOrCustomer(@Param("isbn") String isbn, @Param("customer") String customer, Pageable pageable);

    // O 'Pageable pageable' é opcional. Sem ele vem todos os dados, com ele vai vir paginado de acordo com o que vocÊ mandar
    Page<Loan> findByBook( Book book, Pageable pageable );

    @Query(" select l from Loan l where l.loanDate <= :threeDaysAgo and ( l.returned is null or l.returned is false ) ")
    List<Loan> findByLoanDateLessThanAndNotReturned( @Param("threeDaysAgo") LocalDate threeDaysAgo );
   */
}