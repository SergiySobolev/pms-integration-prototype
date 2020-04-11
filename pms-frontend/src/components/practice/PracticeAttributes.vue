<template>

    <div id="practiceattributes">

        <div class="container">

            <h3 v-if="practice"> Practice <b-badge variant="info">{{practice.name}}</b-badge> details </h3>

            <table class="table table-sm table-bordered">

                <tbody v-if="practice">

                    <tr>
                        <td>ID</td>
                        <td>{{practice.practiceid}}</td>
                    </tr>

                    <tr>
                        <td>Name</td>
                        <td>{{practice.name}}</td>
                    </tr>

                    <tr>
                        <td>Go Live Date</td>
                        <td>{{practice.golivedate}}</td>
                    </tr>

                    <tr>
                        <td>Is Coordinator Sender</td>
                        <td>{{practice.iscoordinatorsender}}</td>
                    </tr>

                    <tr>
                        <td>Has Communicator</td>
                        <td>{{practice.hascommunicator}}</td>
                    </tr>

                    <tr>
                        <td>Has Collector</td>
                        <td>{{practice.hascollector}}</td>
                    </tr>

                </tbody>
            </table>

        </div>

    </div>

</template>

<script>
    export default {
        name: 'PracticeAttributes',
        props: {
            practice: Object
        }
    }
</script>

