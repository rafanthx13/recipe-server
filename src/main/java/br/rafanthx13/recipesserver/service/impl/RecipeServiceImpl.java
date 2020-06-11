package br.rafanthx13.recipesserver.service.impl;

import br.rafanthx13.recipesserver.exception.RegraNegocioException;
import br.rafanthx13.recipesserver.model.dto.PostRecipeDTO;
import br.rafanthx13.recipesserver.model.entity.Badge;
import br.rafanthx13.recipesserver.model.entity.Recipe;
import br.rafanthx13.recipesserver.model.entity.RecipeBadge;
import br.rafanthx13.recipesserver.model.entity.RecipeGet;
import br.rafanthx13.recipesserver.model.repository.*;
import br.rafanthx13.recipesserver.service.BadgeService;
import br.rafanthx13.recipesserver.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.rafanthx13.recipesserver.exception.ApiErrors.errorNotFound;


@Service
public class RecipeServiceImpl implements RecipeService {

  @Autowired
  private RecipeRepository recipeRepository;
  @Autowired
  private RecipeGetRepository recipeGetRepository;
  @Autowired
  private RecipeBadgeRepository recipeBadgeRepository;
  @Autowired
  private BadgeRepository badgeRepository;
  @Autowired
  private BadgeService badgeService;
  @Autowired
  private ImageRepository imageRepository;

  @Override
  public List<Recipe> getAll() {
    return recipeRepository.findAll();
  }

  public List<RecipeGet> getAllRecipes(){
      return recipeGetRepository.findAll().stream().map( recipe -> {
          List<RecipeBadge> listRecipeBadge = recipeBadgeRepository.findReByRecipeId(recipe.getId());
          String imageSource = imageRepository.findOnlyNameById(recipe.getImgSrc()).get();
          // se tiver tira as badges tira de recipeBadgE
          if(listRecipeBadge.size() != 0){
              List<Badge> listBadge = listRecipeBadge.stream()
                      .map(element -> element.getBadge_id())
                      .collect(Collectors.toList());
              recipe.setBadges(listBadge);
          }
          recipe.setImgSource(imageSource);
          return recipe;
      }).collect(Collectors.toList());
  }

    @Override
    public Page<RecipeGet> getAllPageable(Pageable pageable){
        return recipeGetRepository.findAll(pageable).map( recipeGet -> {
            List<RecipeBadge> listRecipeBadge = recipeBadgeRepository.findReByRecipeId(recipeGet.getId());
            String imageSource = imageRepository.findOnlyNameById(recipeGet.getImgSrc()).get();
            // se tiver tira as badges tira de recipeBadgE
            if(listRecipeBadge.size() != 0){
                List<Badge> listBadge = listRecipeBadge.stream()
                        .map(element -> element.getBadge_id())
                        .collect(Collectors.toList());
                recipeGet.setBadges(listBadge);
            }
            recipeGet.setImgSource(imageSource);
            return recipeGet;
        });
    }

  @Override
  public Optional<Recipe> findById(Long id) {
    return recipeRepository.findById(id);
//    return Optional.empty();
  }



    @Override
    public RecipeGet getById(Long id) {
        RecipeGet recipe = recipeGetRepository.findById(id)
                .orElseThrow( () -> errorNotFound("Receita não encontrada"));
        // Pegar Imagem
        if(recipe.getImgSrc() == null){
            throw errorNotFound("Receita não tem Id de imagem");
        }
        String imageSource = imageRepository.findOnlyNameById(recipe.getImgSrc())
                .orElseThrow( () -> errorNotFound("Imagem da receita não encontrada"));
        recipe.setImgSource(imageSource);
        // Badges
        List<RecipeBadge> listRecipeBadge = recipeBadgeRepository.findReByRecipeId(recipe.getId());
        if(listRecipeBadge.size() != 0){
            List<Badge> listBadge = listRecipeBadge.stream()
                    .map(element -> element.getBadge_id())
                    .collect(Collectors.toList());
            recipe.setBadges(listBadge);
        }

        return recipe;
    }


    @Override
    @Transactional
    public void updateRe(PostRecipeDTO recipeWithBadges, Long id){
        Recipe recipe = new Recipe()
                .builder()
                .id(id)
                .imgSrc(recipeWithBadges.getImgSrc())
                .title(recipeWithBadges.getTitle())
                // .badges("1")
                .ingredients(recipeWithBadges.getIngredients())
                .tab_comments(recipeWithBadges.getTab_comments())
                .tab_prepare(recipeWithBadges.getTab_prepare())
                .tab_others(recipeWithBadges.getTab_others())
                .build();
        System.out.println("*********************");
        System.out.println(recipe.getImgSrc());
        Recipe recipeSaved = recipeRepository.save(recipe);
        Long idRecipe = recipeSaved.getId();
        if(recipeWithBadges.getBadges().size() != 0){
            // Salva recipe_badges
            List<RecipeBadge> recipesBadges = convert(recipeWithBadges.getBadges(), idRecipe);
            // apaga todos
            recipeBadgeRepository.deleteByRecipe_id(idRecipe);
//            recipeBadgeRepository.deleteAll();
//            recipeBadgeRepository
            // insere todos
            recipeBadgeRepository.saveAll(recipesBadges);
//            for(RecipeBadge recipeBadge : recipesBadges){
//
//                Optional<RecipeBadge> existRecipeBadge = recipeBadgeRepository.findByRecipe_idAndBadge_id(
//                            recipeBadge.getRecipe_id().getId(),
//                            recipeBadge.getRecipe_id().getId());
//                if(existRecipeBadge.isPresent()){
//                    // nao faz nada pois ja esta la
////                    recipeBadgeRepository
//                } else {
//                    // adiicona novos elementos
//                    recipeBadgeRepository.save(existRecipeBadge.get());
//                }
//            }
//            recipeBadgeRepository.saveAll(recipesBadges);
        }
    }

  // @Override
  // public List<PostRecipeDTO> getAllRecipe() {
  //   List<Recipe> recipes = recipeRepository.findAll();
  //   return recipes.stream().map(recipe -> {
  //     PostRecipeDTO postRecipeDTO = new PostRecipeDTO()
  //             .builder()
  //             .title(recipe.getTitle())
  //             .ingredients(recipe.getIngredients())
  //             .tab_comments(recipe.getTab_comments())
  //             .tab_prepare(recipe.getTab_prepare())
  //             .tab_others(recipe.getTab_others())
  //             .imgSrc(recipe.getImgSrc())
  //             .build();
  //     List<Badge> listBadges = badgeRepository.findById(recipe.getId());
  //     postRecipeDTO.setBadges(listBadges);
  //     return postRecipeDTO;
  //     // pra pegar tem que usar BadgeRepository
  //   }).collect(Collectors.toList());
  // }

  @Override
  public Recipe save(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  // É ESSE QUE É PRA USAR
  @Override
  @Transactional
  public Recipe saveRe(PostRecipeDTO recipeWithBadges) {
    // Fazer dois salvamentos, o da receita e de cada um dos badges
      if(recipeRepository.existsByTitle(recipeWithBadges.getTitle())){
          throw errorNotFound("Receita com esse título já existe");
      }
      if(imageRepository.count() > 25){
          throw errorNotFound("Passou o limite de 25 imagens");
      }
    System.out.println("----------------");
    System.out.println(recipeWithBadges.getImgSrc());
    Recipe recipe = new Recipe()
            .builder()
            .imgSrc(recipeWithBadges.getImgSrc())
            .title(recipeWithBadges.getTitle())
            // .badges("1")
            .ingredients(recipeWithBadges.getIngredients())
            .tab_comments(recipeWithBadges.getTab_comments())
            .tab_prepare(recipeWithBadges.getTab_prepare())
            .tab_others(recipeWithBadges.getTab_others())
            .build();
    System.out.println("*********************");
    System.out.println(recipe.getImgSrc());
    Recipe recipeSaved = recipeRepository.save(recipe);
    Long idRecipe = recipeSaved.getId();

    if(recipeWithBadges.getBadges().size() != 0){
      // Salva recipe_badges
      List<RecipeBadge> recipesBadges = convert(recipeWithBadges.getBadges(), idRecipe);
      recipeBadgeRepository.saveAll(recipesBadges);
    }

    // List<RecipeBadge> recipesBadges = recipeWithBadges.setBadges();

    return recipeSaved;
  }

  private List<RecipeBadge> convert (List<Badge> badges, Long idRecipe){
    return badges.stream().map( badge -> {
        RecipeBadge recipeBadge = new RecipeBadge();
        Long badgeId = badge.getId();

        System.out.println("badgeId");
        System.out.println(badgeId);
        Badge aBadge = badgeService.findById(badgeId)
                .orElseThrow(
                        () -> new RegraNegocioException(
                                "Código33 de produto inválido: "
                        )
                );
        Recipe aRecipe = recipeRepository
                .findById(idRecipe)
                .orElseThrow(
                        () -> new RegraNegocioException(
                                "Código22 de produto inválido: "
                        )
                );
        recipeBadge.setBadge_id(aBadge);
        recipeBadge.setRecipe_id(aRecipe);
        return  recipeBadge;
    }).collect(Collectors.toList());

  }
    /*

     private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();



    }

     */
    /*

    Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
     */


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