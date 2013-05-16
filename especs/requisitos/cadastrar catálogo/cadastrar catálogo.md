# Cadastrar Catálogo

---

Este caso de uso permite o cadastro do catálogo do estabelecimento.

O cadastro será feito manualmente pelo responsável do projeto e acompanhado pelo representante do estabelecimento, que deve fornecer o material necessário para construção do catálogo.

O cadastro de um catálogo será representado por um arquivo xml contendo sua estrutura em árvore, e uma pasta com as imagens de cada produto.

## Fluxo principal

1. Preparar a estrutura do catálogo junto ao representante do estabelecimento.
2. Construir o arquivo xml contendo a estrutura do catálogo.
3. Aquisição do material necessário junto ao representante do estabelecimento.
4. Criar a pasta de imagens.
5. Comprimir arquivo xml juntamente com a pasta de imagens no formato zip.

### Estrutura do xml

O xml terá a seguinte estrutura:
	
	<xml ...>
	<catalog>
		<title>título</title>
		<category>
			<name>categoria1</name>
			<item>
				<name>nome1</name>
				<photo>nome1.jpg</photo>
				<description>description1...</description>
			</item>
		</category>
		<category>
			<name>categoria2</name>
			<category>
				<name>categoria3</name>
				<item>
					<name>nome3</name>
					<photo>nome3.jpg</photo>
					<description>description3...</description>
				</item>
			</category>
			<item>
				<name>nome2</name>
				<photo>nome2.jpg</photo>
				<description>description2...</description>
			</item>
		</category>
	</catalog>

Ou seja, cada categoria possui items ou outras categorias.