<template>

    <div id="practiceDetails">

        <div class="container">

            <h3 v-if="practice"> Practice <b><u>{{practice.name}}</u></b> details </h3>

            <table class="table">

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

            <div class="row">

                <div class="col-md-4">

                    <router-link :to="{ name: 'providers', params: {practiceid: practiceid} }">
                        <input type="button" value="View providers" class="btn btn-primary"/>
                    </router-link>

                </div>

                <div class="col-md-4">

                    <router-link to="/practices">
                        <input type="button" value="Back to Practices" class="btn btn-info"/>
                    </router-link>

                </div>

                <div class="col-md-4">
                    <BackToHome/>
                </div>

            </div>

        </div>

    </div>

</template>

<script>
    import BackToHome from "./BackToHome";
    import {ServicesFactory} from "../services/ServicesFactory";

    const pmsService = ServicesFactory.get("pms");


    export default {
        name: 'PracticeDetails',
        components: {BackToHome},
        props: ['practiceid'],
        data() {
            return {
                practice: null,
            };
        },
        created: function () {
            this.fetch();
        },
        methods: {
            async fetch() {
                pmsService.getPractice(this.practiceid).then(res => {
                    this.practice = res.data;
                });
            }
        }
    }
</script>

