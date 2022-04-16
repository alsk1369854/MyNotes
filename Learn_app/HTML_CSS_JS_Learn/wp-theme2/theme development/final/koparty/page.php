<?php get_header(); ?>

<?php if (have_posts()) : while (have_posts()) : the_post(); ?>

<section id='singlearticle'>
	        
	        <div class="container">
		       
		        <div class='row'>
					  <div class='col-md-8 offset-md-2 text-center'>
						  <img src='<?php the_post_thumbnail_url(); ?>'>
						  <h1><?php the_title(); ?></h1>
						  <span class='jdate'><?php echo get_the_date(); ?></span>
						  <?php the_content(); ?>
					  	
					  </div>
			    <div>
	        </div>
        
        </section>

<?php endwhile; endif; ?>

<?php get_footer(); ?>