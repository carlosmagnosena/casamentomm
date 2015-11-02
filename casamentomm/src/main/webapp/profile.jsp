<jsp:include page="common/header.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">#casamentoMM</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="profile.jsp">Perfil</a></li>
<!--                 <li><a href="gallery.jsp">Galeria</a></li> -->
<!--                 <li><a href="popular.jsp">Popular</a></li> -->
                <li><a href="<s:url action='prepararTag'/>">Carregar Imagens</a></li>
<!--                 <li><a href="logout.jsp">Logout</a></li> -->

            </ul>
        </div>
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">User Profile</h1>
        </div>
       <p class="lead">

        <img src="${imagemDoPerfil}"/> <br/>

        <p>Username : ${nome} </p>

        <p>Followed By : ${seguidoPor} </p>

        <p>Media Count : ${contagemDeImagens}</p>


    </div>

    <hr>


<jsp:include page="common/footer.jsp"/>