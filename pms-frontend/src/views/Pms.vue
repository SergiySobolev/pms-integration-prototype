<template>

        <div>

            <h2> Integrated systems: </h2>

            <br/>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">PMS Name</th>
                        <th scope="col">General Info</th>
                        <th scope="col">Developers Documentation</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <b>AthenaHealth</b>
                        </td>
                        <td>
                            <a href="https://www.athenahealth.com/" target="_blank">
                                <input type="button" value="Info" class="btn btn-info">
                            </a>
                        </td>
                        <td>
                            <a href="https://www.athenahealth.com/developer-portal/developer-toolkit/connectivity" target="_blank">
                                <input type="button" value="Documentation" class="btn btn-info">
                            </a>
                        </td>

                    </tr>
                </tbody>
            </table>

    </div>
     
</template>


<script>
  export default {
    name: 'PMS'
  }
</script>

<style>
  h3 {
    margin-bottom: 5%;
  }
</style>