package com.example.olxclone.util;

import com.example.olxclone.R;
import com.example.olxclone.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Bosco on 07/11/2022.
 */
public class CategoryList {

    public static List<Category> getCategoryList(boolean category) {

        List<Category> categoryList = new ArrayList<>();
        if (category) categoryList.add(new Category(R.drawable.ic_todas_as_categorias, "Todas as categorias"));
        categoryList.add(new Category(R.drawable.ic_autos_e_pecas, "Autos e peças"));
        categoryList.add(new Category(R.drawable.ic_imoveis, "Imóveis"));
        categoryList.add(new Category(R.drawable.ic_eletronico_e_celulares, "Eletrônicos e celulares"));
        categoryList.add(new Category(R.drawable.ic_para_a_sua_casa, "Para a sua casa"));
        categoryList.add(new Category(R.drawable.ic_moda_e_beleza, "Moda e beleza"));
        categoryList.add(new Category(R.drawable.ic_esporte_e_lazer, "Esporte e lazer"));
        categoryList.add(new Category(R.drawable.ic_musica_e_hobbies, "Músicas e hobbies"));
        categoryList.add(new Category(R.drawable.ic_artigos_infantis, "Artigos infantis"));
        categoryList.add(new Category(R.drawable.ic_animais_de_estimacao, "Animais de estimação"));
        categoryList.add(new Category(R.drawable.ic_agro_e_industria, "Agro e indústria"));
        categoryList.add(new Category(R.drawable.ic_comercio_e_escritorio, "Comércio e escritório"));
        categoryList.add(new Category(R.drawable.ic_servicos, "Serviços"));
        categoryList.add(new Category(R.drawable.ic_vagas_de_emprego, "Vagas de emprego"));

        return categoryList;
    }
}
