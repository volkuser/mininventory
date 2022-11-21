<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Equipment control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Inventory number</th>
                        <th scope="col">Weight</th>
                        <th scope="col">Year of entry</th>
                        <th scope="col">Count</th>
                        <th scope="col">Location (number)</th>
                    </tr>
                </thead>
                    <tbody>
                    <#list equipments as equipment>
                        <tr>
                            <td>${equipment.id}</td>
                            <td>${equipment.inventoryNumber}</td>
                            <td>${equipment.weight}</td>
                            <td>${equipment.yearOfEntry}</td>
                            <td>${equipment.count}</td>
                            <td>${equipment.location.number}</td>
                        </tr>
                    <#else>
                        <td>no equipments</td>
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
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/equipment/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/equipment/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new equipment</h5>
                <form method="post">
                    <div class="mb-3">
                        <label class="form-label">Inventory number</label>
                        <label>
                            <input type="text" class="form-control" name="inventoryNumber">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Weight</label>
                        <label>
                            <input type="number" class="form-control" value="0.01" min="0.01" step="0.01" name="weight">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Year of entry</label>
                        <label>
                            <input type="number" class="form-control" name="yearOfEntry"
                                   value="1900" min="1900" max="2999" maxlength="4" minlength="4">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Count</label>
                        <label>
                            <input type="number" class="form-control" value="1" min="1" name="count">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Location</label>
                        <label>
                            <select name="location" class="form-control">
                                <#list locations as location>
                                    <option value="${location.id}">${location.number}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>

</@c.page>