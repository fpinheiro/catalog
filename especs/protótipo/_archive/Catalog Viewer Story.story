<?Infragistics.Models format="xaml" version="2"?>
<Flow xmlns="http://prototypes.infragistics.com/flows"
                                                         xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml">
    <Flow.Items>
        <Step x:Uid="$1" Title="Fernando abre a aplicação" ContentView="/Start.screen" ContentState="dcb77b73-509b-4956-b69b-72881d09a951" X="100" Y="100" Width="430" Height="322" />
        <Step x:Uid="$2" ContentView="/Start.screen" ContentState="d6d20b2a-5114-4810-90ab-fa4101d63857" X="580" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $1}" Target="{Reference $2}" />
        <Step x:Uid="$3" Title="A tela principal exibe cada item do catálogo. Aqui Fernando pode clicar na imagem do item e visualizar sua descrição." ContentView="/Home.screen" ContentState="cc3923b6-7707-4092-9b22-29521d103fc9" X="1060" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $2}" Target="{Reference $3}" />
        <Step x:Uid="$4" Title="Fernando visualiza os detalhes do produto, e pode voltar para a tela principal." ContentView="/Visualizar Produto.screen" ContentState="2dbff94e-9ec5-462a-a9c1-21be6c19dc3a" X="1540" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $3}" Target="{Reference $4}" />
        <Step x:Uid="$5" Title="Novamente na tela principal, Fernando pode visualizar o mapa do catálogo." ContentView="/Home.screen" ContentState="cc3923b6-7707-4092-9b22-29521d103fc9" X="2020" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $4}" Target="{Reference $5}" />
        <Step x:Uid="$6" Title="No mapa do catálogo, Fernando tem uma visão geral do mesmo, e clicando em um item, pode visualizar seus detalhes." ContentView="/Visualizar Estrutura Catálogo.screen" ContentState="98bb506c-56ba-433c-a380-aeedf85da0d1" X="2500" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $5}" Target="{Reference $6}" />
    </Flow.Items>
</Flow>
