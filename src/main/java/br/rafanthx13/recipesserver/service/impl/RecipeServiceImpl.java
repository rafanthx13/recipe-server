package br.rafanthx13.recipesserver.service.impl;

import br.rafanthx13.recipesserver.model.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.service.RecipeService;

import java.util.List;
import java.util.Optional;


@Service
public class RecipeServiceImpl implements RecipeService {

  @Autowired
  private RecipeRepository recipeRepository;

  @Override
  public List<Recipe> getAll() {
    return recipeRepository.findAll();
  }

  @Override
  public Optional<Recipe> findById(Long id) {
    return recipeRepository.findById(id);
//    return Optional.empty();
  }

  @Override
  public Recipe save(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  @Override
  public void delete(Recipe recipe) {
    recipeRepository.delete(recipe);
  }

  // public BookServiceImpl(BookRepository repository) {
  //   this.repository = repository;
  // }

  // @Override // Indica que sobrescreveu metodo da interface
  // public Book save(Book book) {
  //     if( repository.existsByIsbn(book.getIsbn()) ){
  //         throw new BusinessException("Isbn já cadastrado.");
  //     }
  //     return repository.save(book);
  // }

  // @Override
  // public void delete(Book book) {
  //     if(book == null || book.getId() == null){
  //         throw new IllegalArgumentException("Book id cant be null.");
  //     }
  //     this.repository.delete(book);
  // }

  // // get simples sem paginaçõa
  // @Override
  // public Optional<Book> getById(Long id){
  //   return this.repository.findById(id);
  // }
  

  // @Override
  // public Page<Book> find( Book filter, Pageable pageRequest ) {
  //     // VOu criar um Example de acordo com o filter, que será o critério apra buscar os livros filtrados
  //     Example<Book> example = Example.of(filter,
  //             ExampleMatcher // vai permitir fazer as configuraçôes
  //                     .matching()
  //                     .withIgnoreCase() // para as cosia do tipo string, encaixar tanto apra maisuculo quanto minusculo
  //                     .withIgnoreNullValues() // se tiver passado algo null, vai ignorar
  //                     .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING ) 
  //                     // quando for comprar uma string, vai comparar com o seuginte critério: bastar ter um pedaço da palavra
  //     ) ;
  //     return repository.findAll(example, pageRequest);
  // }

  // @Override
  // public Optional<Book> getBookByIsbn(String isbn) {
  //     return repository.findByIsbn(isbn); 
  //     // Query Method. Nâo precisa definir em Repository pois essa Notaçâo é própria da JPA
  // }

  // @Override
  // public Book update(Book book) {
  //     if(book == null || book.getId() == null){
  //         throw new IllegalArgumentException("Book id cant be null.");
  //     }
  //     return this.repository.save(book); // é o meso que o de criar
  // }

 

}