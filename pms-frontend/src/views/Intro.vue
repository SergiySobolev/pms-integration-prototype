<template>

    <div id="nav">

        <h2> Demonstration of integration with <b-badge variant="secondary">Practice Management Systems</b-badge> API </h2>

            <br/><br/>

            <div class="row">

                <div class="col-md-3"></div>

                <div class="col-md-2">

                    <router-link to="/home"><input type="button" value="Start Demo" class="btn btn-primary"/></router-link>

                </div>

                <div class="col-md-2">

                    <router-link to="/architecture"><input type="button" value="Demo's architecture" class="btn btn-info"/></router-link>

                </div>

                <div class="col-md-2">

                    <a href="https://www.usamco.com/pdf/glossary.pdf" target="_blank">

                        <router-link to="/pms"><input type="button" value="About integrated systems" class="btn btn-info"/></router-link>

                    </a>

                </div>

                <div class="col-md-3"></div>

            </div>

        </div>

</template>


<script>
    export default {
        name: 'Intro',
    }
</script>

<style>
    h3 {
        margin-bottom: 5%;
    }
</style>