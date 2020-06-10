package br.rafanthx13.recipesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesServerApplication.class, args);
	}

}

/*
main
+ recipes/get_all : busca todas as receitas so sem a image e nem busca a imagem no back, so id dela
+ image/data_base/1 : busca a imagem

recipe
+ PSOT : recipe/re/  : Funciona no Re
+ PUT
+ DELETE

BD
RECIPE_BADGE: CACADE IN UPDATE AND DLEETE
+ TIRAR COLUNA BADGES DE IMAGE

 */

/*
`mvn install`

`mvn spring-boot:run`

https://www.websparrow.org/spring/spring-boot-display-image-from-database-and-classpath






====================
	Outro caso
====================
// REPOSITORY

package com.javasampleapproach.saveimage2mysql.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.saveimage2mysql.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long>{
}

====================
	CONTROLLER
====================

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.javasampleapproach.saveimage2mysql.jpa.ImageRepository;
import com.javasampleapproach.saveimage2mysql.model.ImageModel;


@SpringBootApplication
public class SpringJpaSaveImage2MySqlApplication implements CommandLineRunner{

	@Autowired
	ImageRepository imageRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSaveImage2MySqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// image 1
		ClassPathResource backImgFile = new ClassPathResource("image/jsa_about_img_black_background.png");
		byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
		backImgFile.getInputStream().read(arrayPic);
		ImageModel blackImage = new ImageModel(1, "JSA-ABOUT-IMAGE-BLACK-BACKGROUND", "png", arrayPic);
		
		// image 2
		ClassPathResource blueImgFile = new ClassPathResource("image/jsa_about_img_blue_background.png");
		arrayPic = new byte[(int) blueImgFile.contentLength()];
		blueImgFile.getInputStream().read(arrayPic);
		ImageModel blueImage = new ImageModel(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);
		
		// store image to MySQL via SpringJPA
		imageRepository.save(blackImage);
		imageRepository.save(blueImage);
		
		// retrieve image from MySQL via SpringJPA
		for(ImageModel imageModel : imageRepository.findAll()){
			Files.write(Paths.get("retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}
	}
}
*/

/*
 	@GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);







        ---------





        // GET PAGINADO E FILTRADO

  @GetMapping // get pra raiz. Quando passar por parametros
  /* vai pegar: ?title=%s&author=%s&page=0&size=100 e encaixar em BookDTO e Pageable
      title/author para BooktDTO e page/size para Pageable

  // @ApiOperation("Lists books by params")
  // 
	  public Page<BookDTO> find( BookDTO dto, Pageable pageRequest ){
	      Book filter = modelMapper.map(dto, Book.class); // converto para Book
	      Page<Book> result = service.find(filter, pageRequest);
	      List<BookDTO> list = result.getContent()
	              .stream() // serve pra agente fazer operaçôes sobre coleções
	              .map(entity -> modelMapper.map(entity, BookDTO.class)) // pra cada elemnto nessa coleçao vai mapear em um DTO
	              .collect(Collectors.toList()); // No final vamos ter uma lsita de BookDDTO
	      return new PageImpl<BookDTO>( list, pageRequest, result.getTotalElements() ); // conteudo, paginal atual, quantidade tottal de elementos
	  }

  // SUB-RECURSO: Obter todos os empréstimos de um livro
  @GetMapping("{id}/loans")
    public Page<LoanDTO> loansByBook( @PathVariable Long id, Pageable pageable ){
        // Tentar identificar o livro pelo ID
        Book book = service.getById(id)
                           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Pega todos os loans pelo book
        Page<Loan> result = loanService.getLoansByBook(book, pageable);
        // Manipula dados de ENtidade para DTO
        List<LoanDTO> list = result.getContent().stream()
                .map(loan -> {
                    Book loanBook = loan.getBook();
                    BookDTO bookDTO = modelMapper.map(loanBook, BookDTO.class);
                    LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
                    loanDTO.setBook(bookDTO);
                    return loanDTO;
                }).collect(Collectors.toList());
        // Retorna lista paginada
        return new PageImpl<LoanDTO>(list, pageable, result.getTotalElements());
    }


    ----------

    INTERFACES


    Page<Loan> find(LoanFilterDTO filterDTO, Pageable pageable);

    Page<Loan> getLoansByBook( Book book, Pageable pageable);

    ----------

	SERVICES IMPL


    // Buscar loan filted. Aqui nâo é um query Method, entâo temod que implementar mesmo no repository
    @Override
    public Page<Loan> find(LoanFilterDTO filterDTO, Pageable pageable) {
        return repository.findByBookIsbnOrCustomer( filterDTO.getIsbn(), filterDTO.getCustomer(), pageable );
    }

    // Busca de SUB-RECURO: Todos os emprestimos de um livro
    @Override
    public Page<Loan> getLoansByBook(Book book, Pageable pageable) {
        return repository.findByBook(book, pageable);
    }

    --------------

    REPOSITORY


    public interface LoanRepository extends JpaRepository<Loan, Long> {

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
	}

	-----------


	https://medium.com/@fabiano_goes/api-rest-com-pagina%C3%A7%C3%A3o-usando-spring-data-e-query-9eddb29c9223

*/