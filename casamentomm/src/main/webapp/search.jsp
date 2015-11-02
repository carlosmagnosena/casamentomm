<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="common/header.jsp"/>

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
                <li><a href="profile.jsp">Perfil</a></li>
<!--                 <li><a href="gallery.jsp">Gallery</a></li> -->
<!--                 <li><a href="popular.jsp">Popular</a></li> -->
                <li class="active"><a href="search.jsp">Carregar Imagens</a></li>
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
            <h1 class="page-header">Carregar</h1>
        </div>

        <p>

        <s:form role ="form" action="carregarImagens" method="POST">
        <s:label for="tag"> Tag #</s:label>
        <s:textfield name="tag" value ="%{tag}"/>
       
        <s:submit class="col-lg-3 col-md-4" cssClass="btn btn-success" value="Carregar" ></s:submit>
                
        
        </s:form>
    
        

            
       <div class="col-lg-3 col-md-4 col-xs-6 thumb">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="${url}"
                     alt="">
            </a>
        </div>

      


    </div>

    <hr>


<jsp:include page="common/footer.jsp"/>