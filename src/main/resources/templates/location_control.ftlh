<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Location control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Number</th>
                    <th scope="col">Letter</th>
                    <th scope="col">Is auditory</th>
                    <th scope="col">Operating hours</th>
                    <th scope="col">Open from</th>
                </tr>
                </thead>
                <tbody>
                <#list locations as location>
                    <tr>
                        <td>${location.id}</td>
                        <td>${location.number}</td>
                        <td>
                            ${location.additionLetter}
                        </td>
                        <td>
                            <#if location.isAuditory()>
                                yes
                            <#else>
                                no
                            </#if>
                        </td>
                        <td>${location.operatingHours}</td>
                        <td>${location.openFrom}</td>
                    </tr>
                <#else>
                    <td>no locations</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <form method="get">
                    <h5>Search</h5>
                    <div class="mb-3">
                        <label>
                            <input type="text" class="form-control" name="query"/>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary" formaction="/location/exact"
                            formmethod="get">Exact search</button>
                    <button type="submit" class="btn btn-primary">Imprecise search</button>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new location</h5>
                <form method="post">
                    <div class="mb-3">
                        <label class="form-label">Number</label>
                        <label>
                            <input type="number" class="form-control" name="number" min="1" value="1">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Addition letter</label>
                        <label>
                            <input type="text" class="form-control" maxlength="1" name="additionLetter">
                        </label>
                    </div>
                    <div class="mb-3 form-check">
                        <label>
                            <input type="checkbox" class="form-check-input" name="isAuditory">
                        </label>
                        <label class="form-check-label">Is auditory</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Operation hours</label>
                        <label>
                            <input type="time" class="form-control" name="operatingHours">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Open from</label>
                        <label>
                            <input type="date" class="form-control" name="openFrom">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>

</@c.page>