<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg custom_nav-container ">
      <a class="navbar-brand" href="/">
        <img src="../img/logo2.png" alt="">
      </a>


      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class=""> </span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <div class="d-flex mr-auto flex-column flex-lg-row align-items-center">
          <ul class="navbar-nav  ">
            <li class="nav-item active">
              <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="?ac=about"> About</a>
            </li>
            <c:if test="${sessionScope.Loggedin != null
                  && sessionScope.Loggedin.equals('1')
                  && (sessionScope.userType == 'ADMIN'
                  || sessionScope.userType == 'WORKER')
                  }">
              <li class="nav-item">
                <a class="nav-link" href="?ac=animal">Animal</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="?ac=food">Food</a>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="?ac=report">Reports</a>
              </li>
            </c:if>

            <c:if test="${sessionScope.Loggedin != null
                  && sessionScope.Loggedin.equals('1')
                  && (sessionScope.userType == 'ADMIN'
                  || sessionScope.userType == 'VISITOR')}">
              <li class="nav-item">
                <a class="nav-link" href="?ac=visit">Visits</a>
              </li>
            </c:if>

          </ul>
        </div>
        <div class="quote_btn-container">
          <c:if test="${sessionScope.Loggedin == null
                  || sessionScope.Loggedin.equals('0') }">
            <a href="?ac=login">
              <i class="fa fa-user" aria-hidden="true"></i>
              <span>
                      Login
                    </span>
            </a>
          </c:if>
          <a href="?ac=signup">
            <i class="fa fa-user" aria-hidden="true"></i>
            <span>
                    Sign Up
                  </span>
          </a>
          <c:if test="${sessionScope.Loggedin != null
                  && sessionScope.Loggedin.equals('1')
                  }">
            <li class="nav-item">
              <a class="nav-link" href="?ac=logout">Logout</a>
            </li>
          </c:if>
          <form class="form-inline">
            <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
              <i class="fa fa-search" aria-hidden="true"></i>
            </button>
          </form>
        </div>
      </div>
    </nav>
</div>