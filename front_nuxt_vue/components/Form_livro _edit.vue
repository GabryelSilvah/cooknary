<template>


  <section class="container_form container_form_edit">

    <form action="POST" @submit.prevent="alterarLivro(livroModel.id_livro)" class="form">
      <img src="public/icones/close.png" id="btn_close" @click="fecharForm">
      <div class="sections">

        <label for="">Titulo do livro</label>
        <input v-model="livroModel.titulo_livro" type="text" placeholder="Ex: Receitas Premium">


        <label for="">Adicione as receitas</label>
        <div class="container_itens_add" id="caixa_de_itens_salvas">
          <h2>Coleção de receitas do livro</h2>
          <div class="container_composicao" v-for="composicao_livro in livroModel.publicacao_receitas_livro">
            <p class="item_lista">{{ composicao_livro.nome_receita }} </p>
            <p class="item_lista">Chef: {{ composicao_livro.cozinheiro_id }} </p>
            <img src="../assets/icones/delete.png" class="img_delete_item"
              @click="excluir_receita_livro(composicao_livro.id_receita)">
          </div>
        </div>


        <select name="receita_livro" id="" v-model="receita_ref">
          <option value="0" disabled>Selecione</option>
          <option :value="receita.id_receita" v-for="receita in listaReceitas" :key="receita.id_receita">
            {{ receita.nome_receita }}
          </option>
        </select>


        <button type="button" @click="addReceitasNaLista" class="btn_adicionar">
          Adicionar
        </button>

        <div class="container_btn_submit">
          <button type="submit" class="btn_submit">
            Atualizar Livro
          </button>
        </div>


      </div>
    </form>

  </section>


</template>
<style scoped>
@import url("~/assets/css_components/form.css");
</style>

<script lang="js" setup>
import { alterarLivros, listarLivros } from '~/common/api/livros_request';
import { byIdAllInfor } from '~/common/api/receitas_request';
import Cookies from 'js-cookie';

//Definindo dados que devem ser pré-carregados
defineProps(
  {
    listaReceitas: Object,
    listaFuncionarios: Object
  }
);

let listaLivros = ref();

//Definindo ligação bilateral com inputs
const receita_ref = defineModel("receita_ref", { default: 0 });
const livroModel = defineModel("livroModel", {
  default:
  {
    titulo_livro: "",
    isbn: 344343,
    editor: { id_func: 0 },
    receitas_remover: [],
    publicacao_receitas_livro: []
  }
});



//Lista de receitas que serão removidas do livro
let listaReceitasExcluir = [];


//Excluir receita do livro
function excluir_receita_livro(id_receita) {

  //Procurando index onde id_receita é igual o id_receita passado para exclusão
  //Se o valor for encontrado, deverá ser removida livroModel
  let index = livroModel.value.publicacao_receitas_livro.findIndex(receita => receita.id_receita == id_receita)

  //Valor for menor que 0 é porque não foi encontrado
  if (index > -1) {
    livroModel.value.publicacao_receitas_livro.splice(index, 1);
  }

  //Adicionando id_receita na lista de receitas a serem deletadas do vinculo com o livro
  listaReceitasExcluir.push(id_receita);
}

//Função para pegar receitas selecionadas e exibir no campo de formulário
async function addReceitasNaLista() {

  //Buscando receita escolhida
  const receitaEncontrada = await byIdAllInfor(receita_ref.value);

  //Encontrar ID na lista de receitas a serem excluidas
  //Se o ID for encontrado na lista, deverá ser removido
  //Após removido, a receita selecionada será adicionada na lista de cadastro do livro
  let index = listaReceitasExcluir.indexOf(receitaEncontrada.id_receita);

  //Valor for menor que 0 é porque não foi encontrado na (listaReceitasExcluir)
  if (index > -1) {
    listaReceitasExcluir.splice(index, 1);
  }


  //Adicionando receita no Objeto livroModel
  let receitaAdicionada = {
    id_receita: receitaEncontrada.id_receita,
    nome_receita: receitaEncontrada.nome_receita,
    cozinheiro_id: receitaEncontrada.cozinheiro_id
  };

  livroModel.value.publicacao_receitas_livro.push(receitaAdicionada);

}


//Atualizar informações do livro
async function alterarLivro(id_livro) {

  let erros = 0;
  if (livroModel.value.publicacao_receitas_livro.length == 0) {
    alert("Adicione ao menos uma receita");
    erros++;
  }

  if (erros == 0) {
    livroModel.value.receitas_remover = listaReceitasExcluir;
    livroModel.value.editor.id_func = Cookies.get("id_user")

    const responseAPI = await alterarLivros(id_livro, livroModel.value);

    if (responseAPI.value.status == "CREATED") {
      fecharForm();
      alert("Livro alterado com sucesso!");
      listaLivros.value = await listarLivros();
    } else {
      alert(responseAPI.value.data.message);
    }
  }
  erros = 0;

}


//Fechar formulário
function fecharForm() {
  listaReceitasExcluir = [];
  livroModel.value = {
    titulo_livro: "",
    isbn: 344343,
    editor: { id_func: 0 },
    receitas_remover: [],
    publicacao_receitas_livro: []
  };

  receita_ref.value = 0;

  let container_form_cadastro = document.querySelector(".container_form_edit");
  container_form_cadastro.setAttribute("style", "display:none");
}

</script>
