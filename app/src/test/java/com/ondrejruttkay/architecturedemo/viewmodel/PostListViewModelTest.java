package com.ondrejruttkay.architecturedemo.viewmodel;

import com.ondrejruttkay.architecturedemo.common.localization.FakeLocalization;
import com.ondrejruttkay.architecturedemo.common.localization.ILocalization;
import com.ondrejruttkay.architecturedemo.common.navigation.INavigator;
import com.ondrejruttkay.architecturedemo.common.repository.FakeRepository;
import com.ondrejruttkay.architecturedemo.common.repository.IRepository;
import com.squareup.otto.Bus;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Onko on 8/11/2016.
 */

public class PostListViewModelTest {

    @Mock
    INavigator navigator;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private PostListComponentViewModel setupPostListViewModel() {
        Bus bus = new Bus();
        IRepository repository = new FakeRepository(bus);
        ILocalization localization = new FakeLocalization(bus);

        PostListComponentViewModel viewModel = new PostListComponentViewModel(bus, repository, navigator, localization);
        return Mockito.spy(viewModel);
    }

    @Test
    public void postLoadingTest() {
        PostListComponentViewModel viewModel = setupPostListViewModel();
        Assert.assertTrue(viewModel.getLoadCommand().isVisible().get());

        viewModel.getLoadCommand().execute();

        Assert.assertFalse(viewModel.getLoadCommand().isVisible().get());
        Assert.assertFalse(viewModel.getIsBusy().get());
        Assert.assertEquals(viewModel.getPosts().size(), 3);
    }

    @Test
    public void deletePostTest() {
        PostListComponentViewModel viewModel = setupPostListViewModel();

        viewModel.getLoadCommand().execute();

        PostComponentViewModel post = viewModel.getPosts().get(0);
        viewModel.deletePost(post);

        Assert.assertEquals(viewModel.getPosts().size(), 2);
        Assert.assertFalse(viewModel.getPosts().contains(post));
    }

    @Test
    public void changeLanguageTest() {
        PostListComponentViewModel viewModel = setupPostListViewModel();

        Assert.assertEquals(viewModel.getLocalization().getLoadButtonLabel(), FakeLocalization.ENGLISH);

        viewModel.toggleLanguage();
        Assert.assertEquals(viewModel.getLocalization().getLoadButtonLabel(), FakeLocalization.SPANISH);
        verify(navigator).showMessage(FakeLocalization.SPANISH);

        viewModel.toggleLanguage();
        Assert.assertEquals(viewModel.getLocalization().getLoadButtonLabel(), FakeLocalization.ENGLISH);
        verify(navigator).showMessage(FakeLocalization.ENGLISH);

        viewModel.toggleLanguage();
        viewModel.toggleLanguage();
        Assert.assertEquals(viewModel.getLocalization().getLoadButtonLabel(), FakeLocalization.ENGLISH);
    }
}
