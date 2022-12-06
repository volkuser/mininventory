<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Equipment list on this location</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Equipment (inventory number)</th>
                    </tr>
                </thead>
                <tbody>
                <#list equipmentsOnLocation as equipment>
                    <tr>
                        <td>${equipment.id}</td>
                        <td>${equipment.inventoryNumber}</td>
                    </tr>
                <#else>
                    <td>no equipment on this location</td>
                </#list>
                </tbody>
            </table>
        </div>
            <div class="p-2">
                <form>
                    <div class="mb-3">
                        <label class="form-label">Also equipment</label>
                        <label>
                            <select name="equipment" class="form-control">
                                <#list otherEquipments as equipment>
                                    <option value="${equipment.id}">${equipment.inventoryNumber}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <a type="button" class="btn btn-primary" href="/location/more/${actualId}">Back</a>
                    <button type="submit" formmethod="post" class="btn btn-primary">Add in list</button>
                </form>
            </div>
        </div>
</@c.page>