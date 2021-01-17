<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Welcome to BitBlog</title>
	<link rel="stylesheet" media="all" href="../css/write.css" />
	<link rel="icon" href="../img/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
</head>
<body>
	<c:set var="Board" value="${b}"/>
	<form method="post" action="board.do?m=modified&email=${loginPassUser.email}&seq=${Board.seq}" enctype="multipart/form-data">		
		<div class="input input__text">
			<input type="text" name="subject" placeholder="제목" class="input" value="<c:out value="${Board.subject}"/>" required />
		</div>
		<div class="input input__textarea">
		    <textarea rows="4" name="content" placeholder="내용" class="input" required><c:out value="${Board.content}"/></textarea>
		</div>
	    <input type="file" id="fileSample1" name="fname" value="">
		<div class="input input__btn">	
		    <button name="submit" class="btn" type="submit">작성</button>
		</div>
	</form>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script type="text/javascript">
	;(function($) {
			  // Browser supports HTML5 multiple file?
			  var multipleSupport = typeof $('<input/>')[0].multiple !== 'undefined',
			      isIE = /msie/i.test( navigator.userAgent );

			  $.fn.customFile = function() {
			    return this.each(function() {
			      var $file = $(this).addClass('custom-file-upload-hidden'), // the original file input
			          $wrap = $('<div class="input input__file">'),
			          $input = $('<input type="text" class="file-upload-input" />'),
			          // Button that will be used in non-IE browsers
			          $button = $('<button type="button" class="file-upload-button">BROWSE</button>'),
			          // Hack for IE
			          $label = $('<label class="file-upload-button" for="'+ $file[0].id +'">BROWSE</label>');

			      // Hide by shifting to the left so we
			      // can still trigger events
			      $file.css({
			        position: 'absolute',
			        left: '-9999px'
			      });

			      $wrap.insertAfter( $file )
			        .append( $file, $input, ( isIE ? $label : $button ) );

			      // Prevent focus
			      $file.attr('tabIndex', -1);
			      $button.attr('tabIndex', -1);

			      $button.click(function () {
			        $file.focus().click(); // Open dialog
			      });

			      $file.change(function() {
			        var files = [], fileArr, filename;
			        // If multiple is supported then extract
			        // all filenames from the file array
			        if ( multipleSupport ) {
			          fileArr = $file[0].files;
			          for ( var i = 0, len = fileArr.length; i < len; i++ ) {
			            files.push( fileArr[i].name );
			          }
			          filename = files.join(', ');

			        // If not supported then just take the value
			        // and remove the path to just show the filename
			        } else {
			          filename = $file.val().split('\\').pop();
			        }

			        $input.val( filename ) // Set the value
			          .attr('title', filename) // Show filename in title tootlip
			          .focus(); // Regain focus
			      });

			      $input.on({
			        blur: function() { $file.trigger('blur'); },
			        keydown: function( e ) {
			          if ( e.which === 13 ) { // Enter
			            if ( !isIE ) { $file.trigger('click'); }
			          } else if ( e.which === 8 || e.which === 46 ) { // Backspace & Del
			            // On some browsers the value is read-only
			            // with this trick we remove the old input and add
			            // a clean clone with all the original events attached
			            $file.replaceWith( $file = $file.clone( true ) );
			            $file.trigger('change');
			            $input.val('');
			          } else if ( e.which === 9 ){ // TAB
			            return;
			          } else { // All other keys
			            return false;
			          }
			        }
			      });
			    });
			  };

			  // Old browser fallback
			  if ( !multipleSupport ) {
			    $( document ).on('change', 'input.customfile', function() {
			      var $this = $(this),
			          // Create a unique ID so we
			          // can attach the label to the input
			          uniqId = 'customfile_'+ (new Date()).getTime(),
			          $wrap = $this.parent(),

			          // Filter empty input
			          $inputs = $wrap.siblings().find('.file-upload-input')
			            .filter(function(){ return !this.value }),
			          $file = $('<input type="file" id="'+ uniqId +'" name="'+ $this.attr('name') +'"/>');

			      // 1ms timeout so it runs after all other events
			      // that modify the value have triggered
			      setTimeout(function() {
			        // Add a new input
			        if ( $this.val() ) {
			          // Check for empty fields to prevent
			          // creating new inputs when changing files
			          if ( !$inputs.length ) {
			            $wrap.after( $file );
			            $file.customFile();
			          }
			        // Remove and reorganize inputs
			        } else {
			          $inputs.parent().remove();
			          // Move the input so it's always last on the list
			          $wrap.appendTo( $wrap.parent() );
			          $wrap.find('input').focus();
			        }
			      }, 1);
			    });
			  }
	}(jQuery));
	$('input[type=file]').customFile();
	</script>
</body>
</html>