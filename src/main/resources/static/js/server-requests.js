//add a button
const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');
const tagRemoveButton = document.querySelector('.remove-tag button');
const tagRemoveInput = document.querySelector('.remove-tag input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		const res = xhr.responseText
		tagsList.innerHTML = res
	}
}
 //for adding tags
tagAddButton.addEventListener('click', function() {
	postTags(tagAddInput.value) 
	tagAddInput.value = ""
})

tagRemoveButton.addEventListener('click', function() {
	var ask = confirm('Are you sure?');
	if (ask == true) {
		removeTags(tagRemoveInput.value)
		tagRemoveInput.value = ""
	}
})

function postTags(tagName) {
	xhr.open('POST', '/show-tags/' + tagName, true)
	xhr.send()
}

function removeTags(tagName) {
	xhr.open('POST', '/show-tags/remove/' + tagName, true)
	xhr.send()
}