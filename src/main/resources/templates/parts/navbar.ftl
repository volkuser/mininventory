<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01"
                aria-expanded="false" aria-label="Navigation switcher">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="#">Mini Inventory Service</a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/equipment">Equipments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/location">Locations</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/commission">Commissions</a>
                </li>
                <#--<li class="nav-item">
                    <a class="nav-link" href="/user">Users</a>
                </li>-->
                <li class="nav-item">
                    <a class="nav-link" href="/employee">Employees</a>
                </li>
            </ul>
            <div>
                <form action="/logout" method="post">
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <input class="btn btn-ark" type="submit" value="Sign Out"/>
                </form>
            </div>
        </div>
    </div>
</nav>